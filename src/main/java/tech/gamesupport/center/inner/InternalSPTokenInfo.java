package tech.gamesupport.center.inner;

public class InternalSPTokenInfo {

    private String token;
    private long expiresAt;
    private String refreshToken;
    private long refreshExpiresAt;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getRefreshExpiresAt() {
        return refreshExpiresAt;
    }

    public void setRefreshExpiresAt(long refreshExpiresAt) {
        this.refreshExpiresAt = refreshExpiresAt;
    }
}