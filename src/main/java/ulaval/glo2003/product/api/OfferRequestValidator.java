package ulaval.glo2003.product.api;

import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.main.api.EmailValidator;
import ulaval.glo2003.main.api.PhoneNumberValidator;
import ulaval.glo2003.product.api.exceptions.InvalidEmailException;
import ulaval.glo2003.product.api.exceptions.InvalidMessageException;
import ulaval.glo2003.product.api.exceptions.InvalidOfferNameException;
import ulaval.glo2003.product.api.exceptions.InvalidPhoneNumberException;
import ulaval.glo2003.product.api.request.OfferRequest;

public class OfferRequestValidator {
  private final ConstraintsValidator constraintsValidator;
  private static final int MIN_MESSAGE_LENGTH = 100;

  public OfferRequestValidator(ConstraintsValidator constraintsValidator) {
    this.constraintsValidator = constraintsValidator;
  }

  public void validate(OfferRequest offerRequest) throws GenericException {
    this.constraintsValidator.validate(offerRequest);

    if (offerRequest.name.isBlank()) {
      throw new InvalidOfferNameException();
    }

    if (!EmailValidator.validate(offerRequest.email)) {
      throw new InvalidEmailException();
    }

    if (!PhoneNumberValidator.validate(offerRequest.phoneNumber)) {
      throw new InvalidPhoneNumberException();
    }

    if (offerRequest.message.length() < MIN_MESSAGE_LENGTH) {
      throw new InvalidMessageException();
    }
  }
}
