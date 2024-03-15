package tech.gamesupport.center.inner;

import tech.gamesupport.center.inner.account.model.UserTokenInfo;

public class RequestOptions {

    private String method;
    private Object body;
    private Object params;
    private boolean needsSPToken = true;
    private UserTokenInfo userTokenInfo;
    private boolean refreshUserToken;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public boolean isNeedsSPToken() {
        return needsSPToken;
    }

    public void setNeedsSPToken(boolean needsSPToken) {
        this.needsSPToken = needsSPToken;
    }

    public UserTokenInfo getUserTokenInfo() {
        return userTokenInfo;
    }

    public void setUserTokenInfo(UserTokenInfo userTokenInfo) {
        this.userTokenInfo = userTokenInfo;
    }

    public boolean isRefreshUserToken() {
        return refreshUserToken;
    }

    public void setRefreshUserToken(boolean refreshUserToken) {
        this.refreshUserToken = refreshUserToken;
    }

    public static class RequestOptionsBuilder {

        private String method;
        private Object body;
        private Object params;
        private boolean needsSPToken = true;
        private UserTokenInfo userTokenInfo;
        private boolean refreshUserToken;

        public RequestOptionsBuilder() {
        }

        public RequestOptionsBuilder post() {
            this.method = "POST";
            return this;
        }

        public RequestOptionsBuilder get() {
            this.method = "GET";
            return this;
        }

        public RequestOptionsBuilder body(Object body) {
            this.body = body;
            return this;
        }

        public RequestOptionsBuilder params(Object params) {
            this.params = params;
            return this;
        }

        public RequestOptionsBuilder needsSPToken(boolean needsSPToken) {
            this.needsSPToken = needsSPToken;
            return this;
        }

        public RequestOptionsBuilder userTokenInfo(UserTokenInfo userTokenInfo) {
            this.userTokenInfo = userTokenInfo;
            return this;
        }

        public RequestOptionsBuilder refreshUserToken(boolean refreshUserToken) {
            this.refreshUserToken = refreshUserToken;
            return this;
        }

        public RequestOptions build() {
            RequestOptions options = new RequestOptions();
            options.setMethod(method);
            options.setBody(body);
            options.setParams(params);
            options.setNeedsSPToken(needsSPToken);
            options.setUserTokenInfo(userTokenInfo);
            options.setRefreshUserToken(refreshUserToken);
            return options;
        }
    }

}
