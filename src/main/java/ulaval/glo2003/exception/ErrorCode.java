package ulaval.glo2003.exception;

public enum ErrorCode {
  MISSING_PARAM(400, "un paramètre (URL, header, JSON, etc.) est manquant"),
  INVALID_PARAM(400, "un paramètre (URL, header, JSON, etc.) est invalide (vide, négatif, trop long. etc.)"),
  ITEM_NOT_FOUND(404, "un élément est introuvable (id invalide ou inexistant)");

  private final int code;
  private final String description;

  ErrorCode(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return this.code;
  }

  public String getDescription() {
    return this.description;
  }

  public ErrorResponse toErrorResponse() {
    return ErrorResponseAssembler.errorCodeToResponse(this);
  }
}
