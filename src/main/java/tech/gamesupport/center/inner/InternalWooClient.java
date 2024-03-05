package tech.gamesupport.center.inner;

import tech.gamesupport.center.inner.account.AccountService;

public class InternalWooClient {

    private final AccountService account;

    private InternalWooClient(ClientConfig clientConfig) {
        account = new AccountService(clientConfig);
    }

    public static WooClientBuilder builder() {
        return new WooClientBuilder();
    }

    public AccountService account() {
        return account;
    }

    public static class WooClientBuilder {
        
        private String baseUrl;
        private String productId;
        private String productSecret;
        private String env;

        public WooClientBuilder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public WooClientBuilder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public WooClientBuilder productSecret(String productSecret) {
            this.productSecret = productSecret;
            return this;
        }

        public WooClientBuilder env(String env) {
            this.env = env;
            return this;
        }

        public InternalWooClient build() {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.setBaseUrl(baseUrl);
            clientConfig.setProductId(productId);
            clientConfig.setProductSecret(productSecret);
            return new InternalWooClient(clientConfig);
        }

    }


}
