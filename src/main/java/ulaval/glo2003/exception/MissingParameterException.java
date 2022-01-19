package ulaval.glo2003.exception;

public class MissingParameterException extends GenericException {

    private ErrorCode errorCode = ErrorCode.MISSING_PARAM;

    public ErrorResponse getErrorResponse() {
        return this.errorCode.toErrorResponse();
    }
}
