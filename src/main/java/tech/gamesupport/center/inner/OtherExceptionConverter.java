package tech.gamesupport.center.inner;

@FunctionalInterface
public interface OtherExceptionConverter {

    RuntimeException convert(ExceptionInfo info);


}
