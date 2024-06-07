package tech.gamesupport.center.inner.core;

public class WooSystemException extends RuntimeException {

    private int httpStatus;

    public WooSystemException(int httpStatus, Throwable cause) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
