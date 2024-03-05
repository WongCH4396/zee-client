package tech.gamesupport.center.inner;

public final class ExceptionInfo {

    private final int httpStatusCode;
    private final Exception exception;

    public ExceptionInfo(int httpStatusCode, Exception exception) {
        this.httpStatusCode = httpStatusCode;
        this.exception = exception;
    }

    public static ExceptionInfo of(int httpStatusCode, Exception e) {
        return new ExceptionInfo(httpStatusCode, e);
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public Exception getException() {
        return exception;
    }

}