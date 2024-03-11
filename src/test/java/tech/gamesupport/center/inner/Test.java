package tech.gamesupport.center.inner;

import com.fasterxml.jackson.databind.ObjectMapper;
import tech.gamesupport.center.inner.account.model.ProductUserTokenInfo;
import tech.gamesupport.center.inner.account.model.UserIdentity;

public class Test {

    public static void main(String[] args) throws Exception {
        InternalWooClient client = InternalWooClient
                .builder()
                .baseUrl("http://localhost:8081")
                .env("dev")
                .productId("pro_albckmilr3mu80346vrxkkslvjqt")
                .productSecret("v44k97LnAW12dJ0iOkcTVZASSJLVyojr")
                .build();

        ProductUserTokenInfo userIdentity = client.account().auth("74ktdc6237ra2wy9");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(userIdentity));
    }
}
