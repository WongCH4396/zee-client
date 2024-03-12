package tech.gamesupport.center.inner.account;

import tech.gamesupport.center.inner.ClientConfig;
import tech.gamesupport.center.inner.RequestOptions;
import tech.gamesupport.center.inner.account.model.InternalUserInfo;
import tech.gamesupport.center.inner.account.model.UserTokenInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class AccountService {

    private final ClientConfig clientConfig;

    public AccountService(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    public UserTokenInfo auth(String ticket) {
        Map<String, Object> map = new HashMap<>();
        map.put("ticket", ticket);
        return clientConfig.sendGet("/account/auth", map, UserTokenInfo.class);
    }

    public InternalUserInfo info(UserTokenInfo userTokenInfo) {
        return clientConfig.sendGet("/account/info", Collections.emptyMap(), InternalUserInfo.class, RequestOptions.ofUser(userTokenInfo));
    }


}
