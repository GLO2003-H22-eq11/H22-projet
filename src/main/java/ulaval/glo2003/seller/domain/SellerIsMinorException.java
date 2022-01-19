package ulaval.glo2003.seller.domain;

import ulaval.glo2003.exception.ErrorCode;
import ulaval.glo2003.exception.ErrorResponse;
import ulaval.glo2003.exception.GenericException;

public class SellerIsMinorException extends GenericException {

    private final ErrorCode errorCode = ErrorCode.INVALID_PARAM;

    public ErrorResponse getErrorResponse() {
        return this.errorCode.toErrorResponse();
    }
}
