package tech.gamesupport.center.inner;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import tech.gamesupport.center.inner.account.model.UserTokenInfo;

import java.util.function.Consumer;

public class ClientConfig {

    private final Object lock = new Object();

    private String baseUrl;
    private String productId;
    private String productSecret;
    private ClientExceptionConverter clientExceptionConverter;
    private OtherExceptionConverter otherExceptionConverter;
    private OkHttpClient httpClient;
    private ObjectMapper objectMapper;
    private Consumer<UserTokenInfo> onUserTokenInfoUpdated;

    private volatile InternalSPTokenInfo spTokenInfo;

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductSecret(String productSecret) {
        this.productSecret = productSecret;
    }

    public void setClientExceptionConverter(ClientExceptionConverter clientExceptionConverter) {
        this.clientExceptionConverter = clientExceptionConverter;
    }

    public void setOtherExceptionConverter(OtherExceptionConverter otherExceptionConverter) {
        this.otherExceptionConverter = otherExceptionConverter;
    }

    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void setOnUserTokenInfoUpdated(Consumer<UserTokenInfo> onUserTokenInfoUpdated) {
        this.onUserTokenInfoUpdated = onUserTokenInfoUpdated;
    }

    public Object getLock() {
        return lock;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductSecret() {
        return productSecret;
    }

    public ClientExceptionConverter getClientExceptionConverter() {
        return clientExceptionConverter;
    }

    public OtherExceptionConverter getOtherExceptionConverter() {
        return otherExceptionConverter;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public InternalSPTokenInfo getSpTokenInfo() {
        return spTokenInfo;
    }

    public void setSpTokenInfo(InternalSPTokenInfo spTokenInfo) {
        this.spTokenInfo = spTokenInfo;
    }

    public Consumer<UserTokenInfo> getOnUserTokenInfoUpdated() {
        return onUserTokenInfoUpdated;
    }
}
