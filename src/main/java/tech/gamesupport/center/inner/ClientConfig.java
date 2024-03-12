package tech.gamesupport.center.inner;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.squareup.okhttp.*;
import tech.gamesupport.center.inner.account.model.UserTokenInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientConfig {

    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final String productId;
    private final String productSecret;

    private String baseUrl;
    private volatile InternalSPTokenInfo spTokenInfo = new InternalSPTokenInfo();

    private ClientExceptionConverter clientExceptionConverter = (code, msg) -> new RuntimeException(msg);
    private OtherExceptionConverter otherExceptionConverter = (e) -> new RuntimeException(e.getException());

    public ClientConfig(String productId, String productSecret) {
        this.productId = productId;
        this.productSecret = productSecret;
    }

    public String fetchSPToken() throws Exception {
        if (spTokenInfo.isTokenValid()) {
            return spTokenInfo.getToken();
        }
        synchronized (this) {
            if (spTokenInfo.isTokenValid()) {
                return spTokenInfo.getToken();
            }
            if (spTokenInfo.isRefreshTokenValid()) {
                spTokenInfo = auth();
                return spTokenInfo.getToken();
            }
            spTokenInfo = auth();
            return spTokenInfo.getToken();
        }
    }

    public UserTokenInfo fetchUserToken(UserTokenInfo userTokenInfo) {
        if (userTokenInfo.isTokenValid()) {
            return userTokenInfo;
        }
        synchronized (this) {
            if (userTokenInfo.isTokenValid()) {
                return userTokenInfo;
            }
            return userTokenInfo;
        }
    }

    private InternalSPTokenInfo auth() throws Exception {
        ObjectMapper objectMapper = getObjectMapper();
        ProductTokenVO vo = new ProductTokenVO();
        vo.setProductId(productId);
        vo.setProductSecret(productSecret);
        RequestBody requestBody = RequestBody
                .create(MediaType.parse("application/json"), objectMapper.writeValueAsString(vo));

        Request.Builder builder = new Request.Builder();
        Request request = builder
                .url(getBaseUrl() + "/api/internal/sp/auth")
                .post(requestBody)
                .build();
        Response response = httpClient
                .newCall(request)
                .execute();
        return handleResponse(response.body().string(), InternalSPTokenInfo.class, objectMapper, getClientExceptionConverter());
    }

    public <T> T sendPost(String path,
                          Object data,
                          Class<T> responseType) {
        int httpStatusCode = -1;
        try {
            ObjectMapper objectMapper = getObjectMapper();
            OkHttpClient httpClient = getHttpClient();
            RequestBody requestBody = RequestBody
                    .create(MediaType.parse("application/json"), objectMapper.writeValueAsString(data));
            // 创建 GET 请求
            Request.Builder builder = new Request.Builder();
            builder.header("Service-Provider-Auth", String.format("Bearer %s", fetchSPToken()));
            Request request = builder
                    .url(getBaseUrl() + "/api/internal" + path)
                    .post(requestBody)
                    .build();
            Response response = httpClient
                    .newCall(request)
                    .execute();
            String responseBody = response.body().string();
            httpStatusCode = response.code();
            return handleResponse(response.body().string(), responseType, objectMapper, getClientExceptionConverter());
        } catch (Exception e) {
            throw getOtherExceptionConverter().convert(ExceptionInfo.of(httpStatusCode, e));
        }
    }

    public <T> T sendGet(String path,
                         Map<String, Object> params,
                         Class<T> responseType) {
        return sendGet(path, params, responseType, null);
    }

    public <T> T sendGet(String path,
                         Map<String, Object> params,
                         Class<T> responseType,
                         RequestOptions options) {
        int httpStatusCode = -1;
        try {
            ObjectMapper objectMapper = getObjectMapper();
            OkHttpClient httpClient = getHttpClient();
            // 创建 GET 请求
            Request.Builder builder = new Request.Builder();
            builder.header("Service-Provider-Auth", String.format("Bearer %s", fetchSPToken()));
            if (options != null) {
                if (options.hasUserToken()) {
                    UserTokenInfo userTokenInfo = fetchUserToken(options.getUserTokenInfo());
                    builder.header("Authorization", String.format("Bearer %s", userTokenInfo.getToken()));
                }
            }
            String queryString = "";
            if (params.size() > 0) {
                queryString = "?" + params
                        .entrySet()
                        .stream()
                        .map(e -> {
                            try {
                                return e.getKey() + "=" + URLEncoder.encode(e.getValue().toString(), "UTF-8");
                            } catch (UnsupportedEncodingException uee) {
                                return null;
                            }
                        }).collect(Collectors.joining("&"));
            }
            Request request = builder
                    .url(getBaseUrl() + "/api/internal" + path + queryString)
                    .get()
                    .build();
            Response response = httpClient
                    .newCall(request)
                    .execute();
            httpStatusCode = response.code();
            return handleResponse(response.body().string(), responseType, objectMapper, getClientExceptionConverter());
        } catch (Exception e) {
            throw getOtherExceptionConverter().convert(ExceptionInfo.of(httpStatusCode, e));
        }
    }

    private <T> T handleResponse(String responseBody,
                                 Class<T> responseType,
                                 ObjectMapper objectMapper,
                                 ClientExceptionConverter clientExceptionConverter) throws Exception {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(WooResponse.class, responseType);
        WooResponse<T> wooResponse = objectMapper.readValue(responseBody, javaType);
        if (wooResponse.getCode() != 0) {
            throw clientExceptionConverter.convert(wooResponse.getCode(), wooResponse.getMessage());
        }
        return wooResponse.getData();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public ClientExceptionConverter getClientExceptionConverter() {
        return clientExceptionConverter;
    }

    public void setClientExceptionConverter(ClientExceptionConverter clientExceptionConverter) {
        this.clientExceptionConverter = clientExceptionConverter;
    }

    public OtherExceptionConverter getOtherExceptionConverter() {
        return otherExceptionConverter;
    }

    public void setOtherExceptionConverter(OtherExceptionConverter otherExceptionConverter) {
        this.otherExceptionConverter = otherExceptionConverter;
    }

}
