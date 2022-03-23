package ulaval.glo2003.product.api.validator;

import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.exceptions.InvalidEmailException;
import ulaval.glo2003.product.api.exceptions.InvalidPhoneNumberException;
import ulaval.glo2003.product.api.request.OfferRequest;

public class OfferRequestValidator {
  private final ConstraintsValidator constraintsValidator;

  public OfferRequestValidator(ConstraintsValidator constraintsValidator) {
    this.constraintsValidator = constraintsValidator;
  }

  public void validate(OfferRequest offerRequest) throws GenericException {
    this.constraintsValidator.validate(offerRequest);

    if (!EmailValidator.validate(offerRequest.email)) {
      throw new InvalidEmailException();
    }

    if (!PhoneNumberValidator.validate(offerRequest.phoneNumber)) {
      throw new InvalidPhoneNumberException();
    }
  }
}
