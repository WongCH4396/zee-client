package tech.gamesupport.center.client;

public class ClientRequestOptions {

    private final boolean useProjectToken;

    public ClientRequestOptions(boolean useProjectToken) {
        this.useProjectToken = useProjectToken;
    }

    public boolean isUseProjectToken() {
        return useProjectToken;
    }

}
