package tech.gamesupport.woo.iapi.service.product.module;

import tech.gamesupport.woo.iapi.core.ClientConfig;
import tech.gamesupport.woo.iapi.core.InternalRequest;
import tech.gamesupport.woo.iapi.core.RequestOptions;
import tech.gamesupport.woo.iapi.service.account.model.UserTokenInfo;
import tech.gamesupport.woo.iapi.service.product.model.OperationLogMessage;

public class LogModule {

    private final ClientConfig clientConfig;

    public LogModule(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    public void recordOperation(OperationLogMessage operationLogMessage, UserTokenInfo userTokenInfo) {
        RequestOptions requestOptions = new RequestOptions.RequestOptionsBuilder()
                .post()
                .userTokenInfo(userTokenInfo)
                .body(operationLogMessage)
                .build();
        InternalRequest.send("/product/log/recordOperation", clientConfig, Void.class, requestOptions);
    }

}
    