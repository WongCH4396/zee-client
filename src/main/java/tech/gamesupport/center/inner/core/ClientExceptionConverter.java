package tech.gamesupport.center.inner.core;

@FunctionalInterface
public interface ClientExceptionConverter {

    RuntimeException convert(int code, String errorMessage);

}
