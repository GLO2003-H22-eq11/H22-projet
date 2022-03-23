package ulaval.glo2003.product.api.request;

import jakarta.validation.constraints.NotNull;
import ulaval.glo2003.exception.GenericRequest;

public class OfferRequest extends GenericRequest {
  @NotNull
  public String name;

  @NotNull
  public String email;

  @NotNull
  public String phoneNumber;

  @NotNull
  public Double amount;

  @NotNull
  public String message;
}
