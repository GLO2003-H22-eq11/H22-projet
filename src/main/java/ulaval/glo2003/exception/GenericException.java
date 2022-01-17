package ulaval.glo2003.exception;

import ulaval.glo2003.exception.ErrorResponse;

public abstract class GenericException extends Throwable {
    public abstract ErrorResponse getErrorResponse();
}
