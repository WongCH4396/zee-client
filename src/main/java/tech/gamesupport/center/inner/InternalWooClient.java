package tech.gamesupport.center.inner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import tech.gamesupport.center.inner.account.AccountService;
import tech.gamesupport.center.inner.account.model.UserTokenInfo;

import java.util.function.Consumer;

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
        private ClientExceptionConverter clientExceptionConverter;
        private OtherExceptionConverter otherExceptionConverter;
        private OkHttpClient httpClient;
        private ObjectMapper objectMapper;
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

        public WooClientBuilder objectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
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
            clientConfig.setObjectMapper(objectMapper);
            clientConfig.setOnUserTokenInfoUpdated(onUserTokenInfoUpdated);
            return new InternalWooClient(clientConfig);
        }

    }


}
