package ulaval.glo2003.main.domain.exception;


import ulaval.glo2003.exception.ErrorCode;
import ulaval.glo2003.exception.ErrorResponse;
import ulaval.glo2003.exception.GenericException;

public class InvalidIdentifierException extends GenericException {
  private final ErrorCode errorCode = ErrorCode.INVALID_PARAMETER;

  public ErrorResponse getErrorResponse() {
    return this.errorCode.toErrorResponse();
  }

  @Override
  public int getStatus() {
    return this.errorCode.getCode();
  }
}
