package tech.gamesupport.center.inner;

import tech.gamesupport.center.inner.account.model.UserIdentity;

public class Test {

    public static void main(String[] args) {
        InternalWooClient client = InternalWooClient
                .builder()
                .baseUrl("http://localhost:8081")
                .env("dev")
                .productId("pro_albckmilr3mu80346vrxkkslvjqt")
                .productSecret("v44k97LnAW12dJ0iOkcTVZASSJLVyojr")
                .build();

        UserIdentity userIdentity = client.account().auth("ir8yuraz6e74dx4v");
        System.out.println("userIdentity = " + userIdentity);
    }
}
