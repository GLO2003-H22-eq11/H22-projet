package ulaval.glo2003.exception;

public class ErrorResponseAssembler {

  public static ErrorResponse errorCodeToResponse(ErrorCode errorCode) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.code = errorCode.toString();
    errorResponse.description = errorCode.getDescription();
    return errorResponse;
  }
}
