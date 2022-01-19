package ulaval.glo2003.exception;

public abstract class GenericException extends Throwable {
    public abstract ErrorResponse getErrorResponse();
    public abstract int getStatus();
}