package ulaval.glo2003.seller.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SellerRequest {
  public final String name;
  public final String bio;
  public final String birthDate;

  public SellerRequest(@JsonProperty(value = "name", required = true) String name,
                       @JsonProperty(value = "bio", required = true) String bio,
                       @JsonProperty(value = "birthDate", required = true) String birthDate
  ) {

    this.name = name;
    this.bio = bio;
    this.birthDate = birthDate;
  }
}