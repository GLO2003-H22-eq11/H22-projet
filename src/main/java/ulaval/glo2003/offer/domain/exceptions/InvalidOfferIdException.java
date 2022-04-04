package ulaval.glo2003.offer.domain.exceptions;

import ulaval.glo2003.exception.ErrorCode;
import ulaval.glo2003.exception.ErrorResponse;
import ulaval.glo2003.exception.GenericException;

public class InvalidOfferIdException extends GenericException {
  private final ErrorCode errorCode = ErrorCode.INVALID_PARAMETER;

  public ErrorResponse getErrorResponse() {
    return this.errorCode.toErrorResponse();
  }

  @Override
  public int getStatus() {
    return this.errorCode.getCode();
  }
}

