package tech.gamesupport.center.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import tech.gamesupport.center.client.auth.model.ProjectAccessTokenVO;
import tech.gamesupport.center.client.auth.model.ProjectTokenInfo;

import java.time.ZonedDateTime;
import java.util.function.Function;

public class ClientState {

    private String baseUrl;
    private String accessToken;
    private long expiresAt;
    private String projectKey;
    private String projectSecret;
    private String env;

    private Function<ZeeClientException, RuntimeException> clientExceptionConverter = RuntimeException::new;
    private Function<Exception, RuntimeException> httpExceptionConverter = RuntimeException::new;
    private Function<Exception, RuntimeException> otherExceptionConverter = RuntimeException::new;


    private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

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
            ProjectAccessTokenVO vo = new ProjectAccessTokenVO();
            vo.setProjectKey(projectKey);
            vo.setProjectSecret(projectSecret);
            vo.setEnv(env);

            ProjectTokenInfo info = ClientRequest
                    .sendPost("/auth/projectAccessToken", vo, ProjectTokenInfo.class, this, new ClientRequestOptions(false));
            this.accessToken = info.getAccessToken();
            this.expiresAt = info.getExpiresAt();
            return accessToken;
        }
    }

    public boolean isTokenValid(String token, long expireAt) {
        return token != null && expireAt > ZonedDateTime.now().toEpochSecond();
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public Function<ZeeClientException, RuntimeException> getClientExceptionConverter() {
        return clientExceptionConverter;
    }

    public Function<Exception, RuntimeException> getHttpExceptionConverter() {
        return httpExceptionConverter;
    }

    public Function<Exception, RuntimeException> getOtherExceptionConverter() {
        return otherExceptionConverter;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public void setProjectSecret(String projectSecret) {
        this.projectSecret = projectSecret;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public void setClientExceptionConverter(Function<ZeeClientException, RuntimeException> clientExceptionConverter) {
        this.clientExceptionConverter = clientExceptionConverter;
    }

    public void setHttpExceptionConverter(Function<Exception, RuntimeException> httpExceptionConverter) {
        this.httpExceptionConverter = httpExceptionConverter;
    }

    public void setOtherExceptionConverter(Function<Exception, RuntimeException> otherExceptionConverter) {
        this.otherExceptionConverter = otherExceptionConverter;
    }
}
