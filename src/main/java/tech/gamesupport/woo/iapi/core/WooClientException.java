package tech.gamesupport.woo.iapi.core;

public class WooClientException extends RuntimeException {

    private final int code;

    public WooClientException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
