package tech.gamesupport.center.client;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;

public class ClientRequest {

    public static <T> T sendPost(String path,
                                      Object data,
                                      Class<T> responseType,
                                      ClientState state,
                                      ClientRequestOptions options) {
        try {
            ObjectMapper objectMapper = state.getObjectMapper();
            OkHttpClient httpClient = state.getHttpClient();
            RequestBody requestBody = RequestBody
                    .create(MediaType.parse("application/json"), objectMapper.writeValueAsString(data));
            // 创建 GET 请求
            Request.Builder builder = new Request.Builder();
            if (options.isUseProjectToken()) {
                builder.header("Authorization", String.format("Bearer %s", state.fetchAccessToken()));
            }
            Request request = builder
                    .url(state.getBaseUrl() + "/api/open" + path)
                    .post(requestBody)
                    .build();
            Response response = httpClient
                    .newCall(request)
                    .execute();
            int httpStatusCode = response.code();
            String responseBody = response.body().string();
            if (httpStatusCode == 200 || httpStatusCode >= 400 && httpStatusCode <= 403) {
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ZeeResponse.class, responseType);
                ZeeResponse<T> zeeResponse = objectMapper.readValue(responseBody, javaType);
                if (zeeResponse.getCode() != 0) {
                    throw new ZeeClientException(zeeResponse.getCode(), zeeResponse.getMessage());
                }
                return zeeResponse.getData();
            }
            throw new ZeeHttpException(httpStatusCode, responseBody);
        } catch (ZeeClientException e) {
            throw state.getClientExceptionConverter().apply(e);
        } catch (ZeeHttpException e) {
            throw state.getHttpExceptionConverter().apply(e);
        } catch (Exception e) {
            throw state.getOtherExceptionConverter().apply(e);
        }
    }

}
