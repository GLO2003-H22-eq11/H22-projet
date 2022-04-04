package ulaval.glo2003.seller.api.validator;

import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.api.exceptions.InvalidSellerBiographyException;
import ulaval.glo2003.seller.api.exceptions.InvalidSellerBirthDateException;
import ulaval.glo2003.seller.api.exceptions.InvalidSellerNameException;
import ulaval.glo2003.seller.api.request.SellerRequest;
import ulaval.glo2003.util.DateParser;

import java.time.LocalDate;

public class SellerRequestValidator {
  private final ConstraintsValidator constraintsValidator;

  public SellerRequestValidator(ConstraintsValidator constraintsValidator) {
    this.constraintsValidator = constraintsValidator;
  }

  public void validate(SellerRequest sellerRequest) throws GenericException {
    this.constraintsValidator.validate(sellerRequest);
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
