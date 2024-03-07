package tech.gamesupport.center.inner.account;

import tech.gamesupport.center.inner.ClientConfig;
import tech.gamesupport.center.inner.account.model.UserIdentity;

import java.util.HashMap;
import java.util.Map;


public class AccountService {

    private final ClientConfig clientConfig;

    public AccountService(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    public UserIdentity auth(String ticket) {
        Map<String, Object> map = new HashMap<>();
        map.put("ticket", ticket);
        return clientConfig.sendGet("/account/auth", map, UserIdentity.class);
    }

}
