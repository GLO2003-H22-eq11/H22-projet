package ulaval.glo2003.exception;

import ulaval.glo2003.exception.ErrorCode;
import ulaval.glo2003.exception.ErrorResponse;
import ulaval.glo2003.exception.GenericException;

public class MissingParameterException extends GenericException {

    private ErrorCode errorCode = ErrorCode.MISSING_PARAM;

    public ErrorResponse getErrorResponse() {
        return this.errorCode.toErrorResponse();
    }
}
