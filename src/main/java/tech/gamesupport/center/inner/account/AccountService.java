package tech.gamesupport.center.inner.account;

import sun.misc.Request;
import tech.gamesupport.center.inner.ClientConfig;
import tech.gamesupport.center.inner.InternalRequest;
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
        RequestOptions requestOptions = new RequestOptions.RequestOptionsBuilder()
                .get()
                .params(map)
                .build();
        return InternalRequest.send("/account/auth", clientConfig, UserTokenInfo.class, requestOptions);
    }

    public InternalUserInfo info(UserTokenInfo userTokenInfo) {
        RequestOptions requestOptions = new RequestOptions.RequestOptionsBuilder()
                .get()
                .userTokenInfo(userTokenInfo)
                .build();
        return InternalRequest.send("/account/info", clientConfig, InternalUserInfo.class, requestOptions);
    }

    public InternalUserInfo legacyInfo(UserTokenInfo userTokenInfo) {
        RequestOptions requestOptions = new RequestOptions.RequestOptionsBuilder()
                .get()
                .userTokenInfo(userTokenInfo)
                .build();
        return InternalRequest.send("/account/legacyInfo", clientConfig, InternalUserInfo.class, requestOptions);
    }


}
