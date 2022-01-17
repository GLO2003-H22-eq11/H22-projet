package ulaval.glo2003.seller.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class SellerRequest {
  @NotBlank
  public String name;

  @NotBlank
  public String bio;

  @NotNull
  public String birthDate;
}