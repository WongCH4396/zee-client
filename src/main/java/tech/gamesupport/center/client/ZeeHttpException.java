package tech.gamesupport.center.client;

public class ZeeHttpException extends RuntimeException {

    private final int httpStatusCode;
    private final String body;

    public ZeeHttpException(int httpStatusCode, String body) {
        this.httpStatusCode = httpStatusCode;
        this.body = body;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getBody() {
        return body;
    }
}
