package tech.gamesupport.center.inner.service.account;

import tech.gamesupport.center.inner.core.ClientConfig;
import tech.gamesupport.center.inner.core.InternalRequest;
import tech.gamesupport.center.inner.core.RequestOptions;
import tech.gamesupport.center.inner.service.account.model.EmptyBody;
import tech.gamesupport.center.inner.service.account.model.InternalUserInfo;
import tech.gamesupport.center.inner.service.account.model.UserTokenInfo;

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

    public void logout(UserTokenInfo userTokenInfo) {
        RequestOptions requestOptions = new RequestOptions.RequestOptionsBuilder()
                .post()
                .body(EmptyBody.INSTANCE)
                .userTokenInfo(userTokenInfo)
                .build();
        InternalRequest.send("/account/logout", clientConfig, String.class, requestOptions);
    }


}
