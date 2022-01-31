package ulaval.glo2003.exception;

public class MissingParameterException extends GenericException {

  private final ErrorCode errorCode = ErrorCode.MISSING_PARAMETER;

  public ErrorResponse getErrorResponse() {
    return this.errorCode.toErrorResponse();
  }

  @Override
  public int getStatus() {
    return this.errorCode.getCode();
  }
}
