package tech.gamesupport.center.inner;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientConfig {

    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private String baseUrl;
    private String accessToken;
    private long expiresAt;
    private String productId;
    private String productSecret;
    private ClientExceptionConverter clientExceptionConverter = null;
    private OtherExceptionConverter otherExceptionConverter = null;

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
            builder.header("Authorization", String.format("Bearer %s", fetchAccessToken()));
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
        int httpStatusCode = -1;
        try {
            ObjectMapper objectMapper = getObjectMapper();
            OkHttpClient httpClient = getHttpClient();
            // 创建 GET 请求
            Request.Builder builder = new Request.Builder();
            builder.header("Authorization", String.format("Bearer %s", fetchAccessToken()));
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

    public String fetchAccessToken() {
        if (isTokenValid(accessToken, expiresAt)) {
            return accessToken;
        }
        synchronized (this) {
            if (isTokenValid(accessToken, expiresAt)) {
                return accessToken;
            }
            ProductTokenVO vo = new ProductTokenVO();
            vo.setProductId(productId);
            vo.setProductSecret(productSecret);
            return accessToken;
        }
    }

    public boolean isTokenValid(String token, long expireAt) {
//        return token != null && expireAt > ZonedDateTime.now().toEpochSecond();
        return true;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductSecret(String productSecret) {
        this.productSecret = productSecret;
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
