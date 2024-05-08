package tech.gamesupport.center.inner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.squareup.okhttp.OkHttpClient;
import tech.gamesupport.center.inner.account.model.InternalUserInfo;
import tech.gamesupport.center.inner.account.model.UserTokenInfo;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.spi.CharsetProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Test {

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .registerModule(new JavaTimeModule());

        InternalWooClient client = InternalWooClient
                .builder()
                .baseUrl("http://localhost:8081")
                .productId("pro_albckmilr3mu80346vrxkkslvjqt")
                .productSecret("v44k97LnAW12dJ0iOkcTVZASSJLVyojr")
                .httpClient(new OkHttpClient())
                .objectMapper(objectMapper)
                .clientExceptionConverter((code, msg) -> new RuntimeException(code + " " + msg))
                .otherExceptionConverter((e) -> new RuntimeException(e.getException()))
                .onUserTokenUpdated((userTokenInfo -> {
                    System.out.println("userTokenInfo = " + userTokenInfo);
                }))
                .build();


//        UserTokenInfo userTokenInfo = client.account().auth("ep37uul5j20in3dh");
        UserTokenInfo userTokenInfo = objectMapper.readValue(tokenObjStr(), UserTokenInfo.class);
        System.out.println("objectMapper.writeValueAsString(info) = " + objectMapper.writeValueAsString(userTokenInfo));
        InternalUserInfo info = client.account().legacyInfo(userTokenInfo);
        System.out.println(objectMapper.writeValueAsString(info));
    }

    public static String tokenObjStr() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        return "{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9kdWN0SWQiOiJwcm9fYWxiY2ttaWxyM211ODAzNDZ2cnhra3NsdmpxdCIsInNlc3Npb25JZCI6ImU4ZmM2ZGJhLWVlMjQtNDgzYy1hNzY0LTI3ZjgzYTU1ODQ1MyIsInR5cGUiOiJiaXoiLCJleHAiOjE3MTUxODg4NTYsInVzZXJJZCI6InVuaV9kdTFtNHJzdW1hNDY0bDZlemJueXplODRqcHhmIiwiaWF0IjoxNzE1MTgxNjU2fQ.4fGKyUCxPBJFUCccvG8eWh9cPxDMe04Gb31BRdjiEZU\",\"expiresAt\":1715188856,\"refreshToken\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9kdWN0SWQiOiJwcm9fYWxiY2ttaWxyM211ODAzNDZ2cnhra3NsdmpxdCIsInNlc3Npb25JZCI6ImU4ZmM2ZGJhLWVlMjQtNDgzYy1hNzY0LTI3ZjgzYTU1ODQ1MyIsInR5cGUiOiJyZWZyZXNoIiwiZXhwIjoxNzE2MzkzNjAwLCJ1c2VySWQiOiJ1bmlfZHUxbTRyc3VtYTQ2NGw2ZXpibnl6ZTg0anB4ZiIsImlhdCI6MTcxNTE4MTY1Nn0.lFmFdFzp5u8HaiQAxV4lmDc7a1xzQvrurtOqpO5N4q4\",\"refreshExpiresAt\":1716393600,\"tokenValid\":true,\"refreshTokenValid\":true}";
    }


}
