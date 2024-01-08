package tech.gamesupport.center.client.org;

import tech.gamesupport.center.client.ClientState;

public class OrgService {


    private final UserEndpoint user;

    public OrgService(ClientState clientState) {
        user = new UserEndpoint(clientState);
    }

    public UserEndpoint user() {
        return user;
    }
}
