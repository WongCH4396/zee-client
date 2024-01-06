package tech.gamesupport.center.client;

public class ClientState {

    private String baseUrl;
    private String projectAccessToken;

    public String getProjectAccessToken() {
        return projectAccessToken;
    }

    public void setProjectAccessToken(String projectAccessToken) {
        this.projectAccessToken = projectAccessToken;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
