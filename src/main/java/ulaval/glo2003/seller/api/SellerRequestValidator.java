package ulaval.glo2003.seller.api;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.api.exceptions.InvalidSellerBiographyException;
import ulaval.glo2003.seller.api.exceptions.InvalidSellerBirthDateException;
import ulaval.glo2003.seller.api.exceptions.InvalidSellerNameException;
import ulaval.glo2003.util.DateParser;

import java.time.LocalDate;

public class SellerRequestValidator {

  public void validate(SellerRequest sellerRequest) throws GenericException {
    if (sellerRequest.name.isBlank()) {
      throw new InvalidSellerNameException();
    }
    if (sellerRequest.bio.isBlank()) {
      throw new InvalidSellerBiographyException();
    }
    if (DateParser.format(sellerRequest.birthDate).isAfter(LocalDate.now())) {
      throw new InvalidSellerBirthDateException();
    }
  }
}
