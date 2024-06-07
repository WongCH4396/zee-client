package tech.gamesupport.woo.iapi.core;

@FunctionalInterface
public interface ClientExceptionConverter {

    RuntimeException convert(int code, String errorMessage);

}
