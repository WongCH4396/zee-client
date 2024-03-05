package tech.gamesupport.center.inner;

@FunctionalInterface
public interface ClientExceptionConverter {

    RuntimeException convert(int code, String errorMessage);

}
