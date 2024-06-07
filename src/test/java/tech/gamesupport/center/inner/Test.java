package tech.gamesupport.center.inner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import tech.gamesupport.center.inner.bean.DBSQLType;
import tech.gamesupport.center.inner.bean.ObjDiff;
import tech.gamesupport.center.inner.core.InternalWooClient;
import tech.gamesupport.center.inner.service.account.model.UserTokenInfo;
import tech.gamesupport.center.inner.service.product.model.OperationLogMessage;
import tech.gamesupport.center.inner.service.product.model.SQLInfo;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;


public class Test {

    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .registerModule(new JavaTimeModule());

    public static void main(String[] args) throws Exception {

        AtomicReference<UserTokenInfo> userTokenInfoRef = new AtomicReference<>();


        InternalWooClient client = InternalWooClient
                .builder()
                .baseUrl("http://localhost:8081")
                .productId("pro_albckmilr3mu80346vrxkkslvjqt")
                .productSecret("v44k97LnAW12dJ0iOkcTVZASSJLVyojr")
                .httpClient(new OkHttpClient.Builder().connectTimeout(Duration.ZERO).readTimeout(Duration.ofSeconds(2)).build())
                .onUserTokenUpdated((newValue -> {
                    System.out.println("newValue = " + OBJECT_MAPPER.valueToTree(newValue).toString());
                    userTokenInfoRef.set(newValue);
                }))
                .build();


        OperationLogMessage message = new OperationLogMessage.Builder()
                .operateKey("user:log:create")
                .operateVersion(1)
                .operateDetail(ObjDiff.from(1000, 2000))
                .sqlInfo(SQLInfo.newBuilder().schema("gcuser").table("operate_log_message").sqlType(DBSQLType.UPDATE).data(new OperationLogMessage()).build())
                .playerUserId(1000)
                .extraData1("money")
                .extraData3("123")
                .operateReason("hello world").build();
        userTokenInfoRef.set(OBJECT_MAPPER.readValue(tokenObjStr2(), UserTokenInfo.class));

        logDuration(() -> client.product().log().recordOperation(message, userTokenInfoRef.get()), "log");
        logDuration(() -> client.account().legacyInfo(userTokenInfoRef.get()), "account legacy info");
    }

    public static String tokenObjStr() {
        return "{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9kdWN0SWQiOiJwcm9fYWxiY2ttaWxyM211ODAzNDZ2cnhra3NsdmpxdCIsInNlc3Npb25JZCI6Ijc2YjUyMWZjLTU1MjktNGQ0Mi1hY2ExLTY2M2I4NjE4OTJmMCIsInR5cGUiOiJiaXoiLCJleHAiOjE3MTc2NzUxMDEsInVzZXJJZCI6InVuaV9paG5rZzgxNmtrbWo4cHpraWVsaWl0dWZ0ZjZkIiwiaWF0IjoxNzE3NjY3OTAxfQ.lg3FUIV-stkFcfSTW6XS2smsBS6S3cw81MUzIWnE7X0\",\"expiresAt\":1717675101,\"refreshToken\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9kdWN0SWQiOiJwcm9fYWxiY2ttaWxyM211ODAzNDZ2cnhra3NsdmpxdCIsInNlc3Npb25JZCI6Ijc2YjUyMWZjLTU1MjktNGQ0Mi1hY2ExLTY2M2I4NjE4OTJmMCIsInR5cGUiOiJyZWZyZXNoIiwiZXhwIjoxNzE4ODk5MjAwLCJ1c2VySWQiOiJ1bmlfaWhua2c4MTZra21qOHB6a2llbGlpdHVmdGY2ZCIsImlhdCI6MTcxNzY2NzkwMX0.82yvS5oAtyvbF7Gyy_LUaB5qo1C89E9AeXyDhy9n7TQ\",\"refreshExpiresAt\":1718899200,\"tokenValid\":true,\"refreshTokenValid\":true}";
    }

    public static String tokenObjStr2() {
        return "{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9kdWN0SWQiOiJwcm9fYWxiY2ttaWxyM211ODAzNDZ2cnhra3NsdmpxdCIsInNlc3Npb25JZCI6Ijc2YjUyMWZjLTU1MjktNGQ0Mi1hY2ExLTY2M2I4NjE4OTJmMCIsInR5cGUiOiJiaXoiLCJleHAiOjE3MTc3Mzg4MTIsInVzZXJJZCI6InVuaV9paG5rZzgxNmtrbWo4cHpraWVsaWl0dWZ0ZjZkIiwiaWF0IjoxNzE3NzMxNjEyfQ.-nSmj4PKwxynC_dJ1_lrCOz1omOwvQLliumUOLf0yNQ\",\"expiresAt\":1717738812,\"refreshToken\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9kdWN0SWQiOiJwcm9fYWxiY2ttaWxyM211ODAzNDZ2cnhra3NsdmpxdCIsInNlc3Npb25JZCI6Ijc2YjUyMWZjLTU1MjktNGQ0Mi1hY2ExLTY2M2I4NjE4OTJmMCIsInR5cGUiOiJyZWZyZXNoIiwiZXhwIjoxNzE4ODk5MjAwLCJ1c2VySWQiOiJ1bmlfaWhua2c4MTZra21qOHB6a2llbGlpdHVmdGY2ZCIsImlhdCI6MTcxNzczMTYxMn0.oMc2b6XCuTXqaR6hSDwl86e5xVYjPbsv6dPdwtL8QiQ\",\"refreshExpiresAt\":1718899200,\"tokenValid\":true,\"refreshTokenValid\":true}";
    }

    public static void logDuration(Runnable execution, String name) {
        Instant before = Instant.now();
        execution.run();
        Instant after = Instant.now();
        System.out.println(name + " used " + Duration.between(before, after));
    }


}
