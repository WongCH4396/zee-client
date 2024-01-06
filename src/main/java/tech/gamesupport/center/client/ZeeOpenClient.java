package tech.gamesupport.center.client;

import tech.gamesupport.center.client.org.OrgService;
import tech.gamesupport.center.client.org.model.OpenUserInfo;

public class ZeeOpenClient {

    private String openUrl;
    private String projectKey;
    private String projectSecret;
    private String env;

    private ClientState clientState = new ClientState();

    private OrgService org;

    private ZeeOpenClient() {
        org = new OrgService(clientState);
    }

    public OrgService org() {
        return org;
    }

    public static ZeeOpenClientBuilder builder() {
        return new ZeeOpenClientBuilder();
    }


    public static class ZeeOpenClientBuilder {
        private String openUrl;
        private String projectKey;
        private String projectSecret;
        private String env;

        public ZeeOpenClientBuilder openUrl(String openUrl) {
            this.openUrl = openUrl;
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
            ZeeOpenClient client = new ZeeOpenClient();
            client.openUrl = openUrl;
            client.projectKey = projectKey;
            client.projectSecret = projectSecret;
            client.env = env;
            return client;
        }

    }


}
