package ulaval.glo2003.exception;

import ulaval.glo2003.exception.ErrorCode;
import ulaval.glo2003.exception.ErrorResponse;

public class ErrorResponseAssembler {

    public static ErrorResponse errorCodeToResponse(ErrorCode errorCode) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.code = errorCode.getCode();
        errorResponse.description = errorCode.getDescription();
        return errorResponse;
    }
}
