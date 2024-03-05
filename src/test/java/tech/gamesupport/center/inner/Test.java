package tech.gamesupport.center.inner;

import tech.gamesupport.center.inner.account.model.UserIdentity;

public class Test {

    public static void main(String[] args) {
        InternalWooClient client = InternalWooClient
                .builder()
                .baseUrl("http://localhost:8081")
                .env("dev")
                .productId("pro_4w1hjuzx8df9zhk3")
                .productSecret("GfbhSiyw3A3rIGoYlII1gWPaKIwvsq6j")
                .build();

        UserIdentity userIdentity = client.account().fetchCookie("7j5lc6ai0uc6yaf8");
        System.out.println("userIdentity = " + userIdentity);
    }
}
