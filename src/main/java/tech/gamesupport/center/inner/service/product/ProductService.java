package tech.gamesupport.center.inner.service.product;

import tech.gamesupport.center.inner.core.ClientConfig;
import tech.gamesupport.center.inner.service.product.module.LogModule;

public class ProductService {

    private final ClientConfig clientConfig;

    private LogModule log;

    public ProductService(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        this.log = new LogModule(clientConfig);
    }

    public LogModule log() {
        return log;
    }



}
