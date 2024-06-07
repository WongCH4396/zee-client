package tech.gamesupport.woo.iapi.service.product;

import tech.gamesupport.woo.iapi.core.ClientConfig;
import tech.gamesupport.woo.iapi.service.product.module.LogModule;

public class ProductService {

    private final LogModule log;

    public ProductService(ClientConfig clientConfig) {
        this.log = new LogModule(clientConfig);
    }

    public LogModule log() {
        return log;
    }



}
