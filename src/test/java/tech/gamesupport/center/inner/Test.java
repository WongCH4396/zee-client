package tech.gamesupport.center.inner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import tech.gamesupport.center.inner.account.model.UserTokenInfo;
import tech.gamesupport.center.inner.account.model.InternalUserInfo;

public class Test {

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .registerModule(new JavaTimeModule());
        InternalWooClient client = InternalWooClient
                .builder()
                .baseUrl("http://localhost:8081")
                .env("dev")
                .productId("pro_albckmilr3mu80346vrxkkslvjqt")
                .productSecret("v44k97LnAW12dJ0iOkcTVZASSJLVyojr")
                .build();

//        UserTokenInfo userTokenInfo = client.account().auth("vz93izl57v0m0rpu");
        UserTokenInfo userTokenInfo = objectMapper.readValue(tokenObjStr(), UserTokenInfo.class);
        System.out.println("objectMapper.writeValueAsString(info) = " + objectMapper.writeValueAsString(userTokenInfo));
        InternalUserInfo info = client.account().info(userTokenInfo);
        System.out.println(objectMapper.writeValueAsString(info));
    }

    public static String tokenObjStr() {
        return "{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9kdWN0SWQiOiJwcm9fYWxiY2ttaWxyM211ODAzNDZ2cnhra3NsdmpxdCIsInNlc3Npb25JZCI6ImRhNjdhNWY2LTcyZmItNDAyMy1iYWRmLTgzNzhhMjM1NDJjYyIsInR5cGUiOiJiaXoiLCJleHAiOjE3MTAyNTAwOTYsInVzZXJJZCI6InVuaV85NWZuZHVqbGdxbmtkejF3Z21vOHUzMDl0bjZjIiwiaWF0IjoxNzEwMjQyODk2fQ.wquLp2-ydPMEaUIJO6RF3-16mevEbPiWlSJiD3Tql7o\",\"expiresAt\":1710250096,\"refreshToken\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9kdWN0SWQiOiJwcm9fYWxiY2ttaWxyM211ODAzNDZ2cnhra3NsdmpxdCIsInNlc3Npb25JZCI6ImRhNjdhNWY2LTcyZmItNDAyMy1iYWRmLTgzNzhhMjM1NDJjYyIsInR5cGUiOiJyZWZyZXNoIiwiZXhwIjoxNzExNDY4ODAwLCJ1c2VySWQiOiJ1bmlfOTVmbmR1amxncW5rZHoxd2dtbzh1MzA5dG42YyIsImlhdCI6MTcxMDI0Mjg5Nn0.JtT95C3MK47Z8wd33urM1t5vUQBQI9K8UHqkr5bHuPo\",\"refreshExpiresAt\":1711468800,\"refreshTokenValid\":true,\"tokenValid\":true}";
    }

}
