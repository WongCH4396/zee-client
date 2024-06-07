package tech.gamesupport.center.inner.core;

import okhttp3.OkHttpClient;
import tech.gamesupport.center.inner.service.account.AccountService;
import tech.gamesupport.center.inner.service.account.model.UserTokenInfo;
import tech.gamesupport.center.inner.service.product.ProductService;

import java.util.function.Consumer;

public class InternalWooClient {

    private final AccountService account;
    private final ProductService product;

    private InternalWooClient(ClientConfig clientConfig) {
        account = new AccountService(clientConfig);
        product = new ProductService(clientConfig);
    }

    public static WooClientBuilder builder() {
        return new WooClientBuilder();
    }

    public AccountService account() {
        return account;
    }

    public ProductService product() {
        return product;
    }

    public static class WooClientBuilder {

        private String baseUrl;
        private String productId;
        private String productSecret;
        private ClientExceptionConverter clientExceptionConverter = WooClientException::new;
        private OtherExceptionConverter otherExceptionConverter = WooSystemException::new;
        private OkHttpClient httpClient;
        private Consumer<UserTokenInfo> onUserTokenInfoUpdated;

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

        public WooClientBuilder clientExceptionConverter(ClientExceptionConverter clientExceptionConverter) {
            this.clientExceptionConverter = clientExceptionConverter;
            return this;
        }

        public WooClientBuilder otherExceptionConverter(OtherExceptionConverter otherExceptionConverter) {
            this.otherExceptionConverter = otherExceptionConverter;
            return this;
        }

        public WooClientBuilder httpClient(OkHttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public WooClientBuilder onUserTokenUpdated(Consumer<UserTokenInfo> onUserTokenInfoUpdated) {
            this.onUserTokenInfoUpdated = onUserTokenInfoUpdated;
            return this;
        }

        public InternalWooClient build() {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.setBaseUrl(baseUrl);
            clientConfig.setProductId(productId);
            clientConfig.setProductSecret(productSecret);
            clientConfig.setClientExceptionConverter(clientExceptionConverter);
            clientConfig.setOtherExceptionConverter(otherExceptionConverter);
            clientConfig.setHttpClient(httpClient);
            clientConfig.setObjectMapper(Globals.serializeObjectMapper);
            clientConfig.setOnUserTokenInfoUpdated(onUserTokenInfoUpdated);
            return new InternalWooClient(clientConfig);
        }

    }


}
