package tech.gamesupport.center.client;

import tech.gamesupport.center.client.org.OrgService;

public class ZeeOpenClient {

    private final ClientState clientState = new ClientState();

    private OrgService org;

    private ZeeOpenClient(ClientState clientState) {
        org = new OrgService(clientState);
    }

    public OrgService org() {
        return org;
    }

    public static ZeeOpenClientBuilder builder() {
        return new ZeeOpenClientBuilder();
    }

    public static class ZeeOpenClientBuilder {
        private String baseUrl;
        private String projectKey;
        private String projectSecret;
        private String env;

        public ZeeOpenClientBuilder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public ZeeOpenClientBuilder projectKey(String projectKey) {
            this.projectKey = projectKey;
            return this;
        }

        public ZeeOpenClientBuilder projectSecret(String projectSecret) {
            this.projectSecret = projectSecret;
            return this;
        }

        public ZeeOpenClientBuilder env(String env) {
            this.env = env;
            return this;
        }

        public ZeeOpenClient build() {
            ClientState clientState = new ClientState();
            clientState.setBaseUrl(baseUrl);
            clientState.setProjectKey(projectKey);
            clientState.setProjectSecret(projectSecret);
            clientState.setEnv(env);
            return new ZeeOpenClient(clientState);
        }

    }




}
