package tech.gamesupport.woo.iapi.core;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import tech.gamesupport.woo.iapi.core.model.InternalSPTokenInfo;
import tech.gamesupport.woo.iapi.core.model.ProductTokenVO;
import tech.gamesupport.woo.iapi.core.model.WooResponse;
import tech.gamesupport.woo.iapi.service.account.model.UserTokenInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class InternalRequest {

    public static <T> T send(String path,
                             ClientConfig clientConfig,
                             Class<T> responseType,
                             RequestOptions options) {
        int httpStatus = -1;
        WooResponse<T> wooResponse;
        try {
            ObjectMapper objectMapper = clientConfig.getObjectMapper();
            OkHttpClient httpClient = clientConfig.getHttpClient();
            RequestBody requestBody = null;
            if (options.getBody() != null) {
                requestBody = RequestBody
                        .create(MediaType.parse("application/json"), objectMapper.writeValueAsString(options.getBody()));
            }
            String queryString = "";
            if (options.getParams() != null) {
                Object params = options.getParams();
                if (params instanceof Map && ((Map<?, ?>) params).size() > 0) {
                    queryString = "?" + ((Map<?, ?>) params)
                            .entrySet()
                            .stream()
                            .map(e -> {
                                try {
                                    return e.getKey() + "=" + URLEncoder.encode(e.getValue().toString(), "UTF-8");
                                } catch (UnsupportedEncodingException uee) {
                                    return null;
                                }
                            }).collect(Collectors.joining("&"));
                } else {
                    JsonNode node = objectMapper.valueToTree(params);
                    Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
                    StringJoiner stringJoiner = new StringJoiner("&");
                    boolean added = false;
                    while (fields.hasNext()) {
                        Map.Entry<String, JsonNode> entry = fields.next();
                        String key = entry.getKey();
                        JsonNode value = entry.getValue();
                        if (value == null || value.isNull()) {
                            continue;
                        }
                        added = true;
                        String val = entry.getValue().asText();
                        stringJoiner.add(key + "=" + URLEncoder.encode(val, "UTF-8"));
                    }
                    if (added) {
                        queryString = "?" + stringJoiner.toString();
                    }
                }
            }

            Request.Builder builder = new Request.Builder();
            if (options.isNeedsSPToken()) {
                builder.header("Service-Provider-Auth", String.format("Bearer %s", fetchSPToken(clientConfig)));
            }
            if (options.getUserTokenInfo() != null) {
                UserTokenInfo userTokenInfo = options.getUserTokenInfo();
                if (options.isRefreshUserToken()) {
                    builder.header("Authorization", String.format("Bearer %s", userTokenInfo.getRefreshToken()));
                } else {
                    builder.header("Authorization", String.format("Bearer %s", fetchUserToken(userTokenInfo, clientConfig)));
                }
            }
            Request request = builder
                    .url(clientConfig.getBaseUrl() + "/api/internal" + path + queryString)
                    .method(options.getMethod(), requestBody)
                    .build();
            Response response = httpClient
                    .newCall(request)
                    .execute();
            String responseBody = response.body().string();
            httpStatus = response.code();
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(WooResponse.class, responseType);
            wooResponse = objectMapper.readValue(responseBody, javaType);
        } catch (Exception e) {
            throw clientConfig.getOtherExceptionConverter().convert(httpStatus, e);
        }
        if (wooResponse.getCode() != 0) {
            throw clientConfig.getClientExceptionConverter().convert(wooResponse.getCode(), wooResponse.getMessage());
        }
        return wooResponse.getData();
    }

    private static String fetchSPToken(final ClientConfig clientConfig) {
        InternalSPTokenInfo spTokenInfo = clientConfig.getSpTokenInfo();
        if (spTokenInfo != null && spTokenInfo.isTokenValid()) {
            return spTokenInfo.getToken();
        }
        synchronized (clientConfig.getLock()) {
            if (spTokenInfo != null && spTokenInfo.isTokenValid()) {
                return spTokenInfo.getToken();
            }
            ProductTokenVO vo = new ProductTokenVO();
            vo.setProductId(clientConfig.getProductId());
            vo.setProductSecret(clientConfig.getProductSecret());

            RequestOptions requestOptions = new RequestOptions.RequestOptionsBuilder()
                    .post()
                    .body(vo)
                    .needsSPToken(false)
                    .build();
            InternalSPTokenInfo internalSPTokenInfo = InternalRequest.send("/sp/auth", clientConfig, InternalSPTokenInfo.class, requestOptions);
            clientConfig.setSpTokenInfo(internalSPTokenInfo);
            return internalSPTokenInfo.getToken();
        }
    }

    private static String fetchUserToken(final UserTokenInfo currentTokenInfo, ClientConfig clientConfig) {
        if (currentTokenInfo.isTokenValid()) {
            return currentTokenInfo.getToken();
        }
        RequestOptions requestOptions = new RequestOptions.RequestOptionsBuilder()
                .get()
                .needsSPToken(true)
                .userTokenInfo(currentTokenInfo)
                .refreshUserToken(true)
                .build();
        UserTokenInfo refreshedTokenInfo = InternalRequest.send("/account/refresh", clientConfig, UserTokenInfo.class, requestOptions);
        clientConfig.getOnUserTokenInfoUpdated().accept(refreshedTokenInfo);
        return refreshedTokenInfo.getToken();
    }


}
