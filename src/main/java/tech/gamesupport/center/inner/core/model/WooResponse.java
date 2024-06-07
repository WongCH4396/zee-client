package tech.gamesupport.center.inner.core.model;

public class WooResponse<T> {

    private int code;
    private String message;
    private Exception nativeException;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getNativeException() {
        return nativeException;
    }

    public void setNativeException(Exception nativeException) {
        this.nativeException = nativeException;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
