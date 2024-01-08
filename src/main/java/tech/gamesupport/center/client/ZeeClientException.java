package tech.gamesupport.center.client;

public class ZeeClientException extends RuntimeException {

    private final int code;

    public ZeeClientException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
