package tech.gamesupport.center.client.org;

import tech.gamesupport.center.client.ClientState;
import tech.gamesupport.center.client.org.model.OpenUserInfo;

public class UserEndpoint {

    private ClientState clientState;

    UserEndpoint(ClientState clientState) {
        this.clientState = clientState;
    }

    public OpenUserInfo exchange(String code) {
        clientState.fetchAccessToken();
        return null;
    }


}