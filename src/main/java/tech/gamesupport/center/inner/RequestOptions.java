package tech.gamesupport.center.inner;

import tech.gamesupport.center.inner.account.model.UserTokenInfo;

public class RequestOptions {

    private UserTokenInfo userTokenInfo;

    public boolean hasUserToken() {
        return userTokenInfo != null;
    }

    public static RequestOptions ofUser(UserTokenInfo userTokenInfo) {
        RequestOptions options = new RequestOptions();
        options.setUserTokenInfo(userTokenInfo);
        return options;
    }

    public UserTokenInfo getUserTokenInfo() {
        return userTokenInfo;
    }

    public void setUserTokenInfo(UserTokenInfo userTokenInfo) {
        this.userTokenInfo = userTokenInfo;
    }
}
