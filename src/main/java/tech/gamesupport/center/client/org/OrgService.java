package tech.gamesupport.center.client.org;

import tech.gamesupport.center.client.ClientState;
import tech.gamesupport.center.client.org.model.OpenUserInfo;

public class OrgService {

    private ClientState clientState;

    private UserService user;

    public OrgService(ClientState clientState) {
        this.clientState = clientState;
    }

    public UserService user() {
        return user;
    }

    public static final class UserService {

        private ClientState clientState;

        private UserService(ClientState clientState) {
            this.clientState = clientState;
        }

        public OpenUserInfo exchange(String code) {
            return null;
        }


    }

}
