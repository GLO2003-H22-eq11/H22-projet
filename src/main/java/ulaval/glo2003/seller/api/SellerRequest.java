package ulaval.glo2003.seller.api;

import jakarta.validation.constraints.NotNull;
import ulaval.glo2003.exception.GenericRequest;

public class SellerRequest extends GenericRequest {
  @NotNull
  public String name;

  @NotNull
  public String bio;

  @NotNull
  public String birthDate;
}
