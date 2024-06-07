package tech.gamesupport.center.inner.core;

@FunctionalInterface
public interface OtherExceptionConverter {

    RuntimeException convert(int httpStatus, Exception cause);


}
