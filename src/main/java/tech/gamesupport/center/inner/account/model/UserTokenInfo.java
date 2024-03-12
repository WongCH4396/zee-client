package tech.gamesupport.center.inner.account.model;

import java.time.ZonedDateTime;

public class UserTokenInfo {

    private String token;
    private long expiresAt;
    private String refreshToken;
    private long refreshExpiresAt;

    public boolean isTokenValid() {
        return expiresAt > ZonedDateTime.now().toEpochSecond();
    }

    public boolean isRefreshTokenValid() {
        return refreshExpiresAt > ZonedDateTime.now().toEpochSecond();
    }

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