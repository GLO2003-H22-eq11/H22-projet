package ulaval.glo2003.seller.service;

import ulaval.glo2003.exception.ErrorCode;
import ulaval.glo2003.exception.ErrorResponse;
import ulaval.glo2003.exception.GenericException;

public class SellerNotFoundException extends GenericException {

  private final ErrorCode errorCode = ErrorCode.ITEM_NOT_FOUND;

  @Override
  public ErrorResponse getErrorResponse() {
    return this.errorCode.toErrorResponse();
  }
}
