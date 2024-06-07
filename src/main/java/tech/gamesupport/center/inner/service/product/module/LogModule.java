package tech.gamesupport.center.inner.service.product.module;

import tech.gamesupport.center.inner.core.ClientConfig;
import tech.gamesupport.center.inner.core.InternalRequest;
import tech.gamesupport.center.inner.core.RequestOptions;
import tech.gamesupport.center.inner.service.account.model.UserTokenInfo;
import tech.gamesupport.center.inner.service.product.model.OperationLogMessage;

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
    