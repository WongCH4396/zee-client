package tech.gamesupport.woo.iapi.core;

@FunctionalInterface
public interface OtherExceptionConverter {

    RuntimeException convert(int httpStatus, Exception cause);


}
