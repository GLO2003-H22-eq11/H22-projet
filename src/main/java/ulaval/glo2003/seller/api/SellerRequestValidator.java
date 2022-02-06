package ulaval.glo2003.seller.api;

import ulaval.glo2003.seller.api.exceptions.InvalidSellerBiographyException;
import ulaval.glo2003.seller.api.exceptions.InvalidSellerNameException;

public class SellerRequestValidator {

  public void validate(SellerRequest sellerRequest) throws
          InvalidSellerNameException,
          InvalidSellerBiographyException {
    if (sellerRequest.name.isBlank()) {
      throw new InvalidSellerNameException();
    }
    if (sellerRequest.bio.isBlank()) {
      throw new InvalidSellerBiographyException();
    }
  }
}
