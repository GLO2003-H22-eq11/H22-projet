package ulaval.glo2003.product.api.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.exceptions.InvalidEmailException;
import ulaval.glo2003.product.domain.exceptions.InvalidMessageException;
import ulaval.glo2003.product.domain.exceptions.InvalidOfferNameException;
import ulaval.glo2003.product.api.exceptions.InvalidPhoneNumberException;
import ulaval.glo2003.product.api.request.OfferRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OfferRequestValidatorTest {
  private static final String A_NAME = "MARINO";
  private static final String A_VALID_EMAIL = "marinoboi@email.ca";
  private static final String A_VALID_PHONE_NUMBER = "14181234567";
  private static final Double AN_AMOUNT = 50.5;
  private static final String A_VALID_MESSAGE_WITH_100_CHARACTERS = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.";

  private final OfferRequest A_VALID_OFFER_REQUEST = this.givenAnOfferRequest(A_NAME, A_VALID_EMAIL, A_VALID_PHONE_NUMBER, AN_AMOUNT, A_VALID_MESSAGE_WITH_100_CHARACTERS);

  @Mock
  private ConstraintsValidator constraintsValidator;

  private OfferRequestValidator offerRequestValidator;

  @BeforeEach
  public void setUp() {
    this.offerRequestValidator = new OfferRequestValidator(this.constraintsValidator);
  }

  @Test
  public void givenAnOfferRequest_whenValidate_thenShouldValidateWithConstraintsValidator() throws GenericException {
    this.offerRequestValidator.validate(A_VALID_OFFER_REQUEST);

    verify(this.constraintsValidator).validate(A_VALID_OFFER_REQUEST);
  }

  @Test
  public void givenAValidOfferRequest_whenValidate_thenShouldNotThrow() {
    assertDoesNotThrow(() -> this.offerRequestValidator.validate(A_VALID_OFFER_REQUEST));
  }


  @Test
  public void givenAnInvalidOfferRequestWithAnInvalidEmail_whenValidate_thenShouldThrowInvalidEmailException() {
    String anInvalidEmail = "marin.ca";
    OfferRequest offerRequest = this.givenAnOfferRequest(A_NAME, anInvalidEmail, A_VALID_PHONE_NUMBER, AN_AMOUNT, A_VALID_MESSAGE_WITH_100_CHARACTERS);

    assertThrows(InvalidEmailException.class, () -> this.offerRequestValidator.validate(offerRequest));
  }

  @Test
  public void givenAnInvalidOfferRequestWithAnInvalidPhoneNumber_whenValidate_thenShouldThrowInvalidPhoneNumberException() {
    String anInvalidPhoneNumber = "418123456";
    OfferRequest offerRequest = this.givenAnOfferRequest(A_NAME, A_VALID_EMAIL, anInvalidPhoneNumber, AN_AMOUNT, A_VALID_MESSAGE_WITH_100_CHARACTERS);

    assertThrows(InvalidPhoneNumberException.class, () -> this.offerRequestValidator.validate(offerRequest));
  }


  private OfferRequest givenAnOfferRequest(String name, String email, String phoneNumber, Double amount, String message) {
    OfferRequest offerRequest = new OfferRequest();
    offerRequest.name = name;
    offerRequest.email = email;
    offerRequest.phoneNumber = phoneNumber;
    offerRequest.amount = amount;
    offerRequest.message = message;
    return offerRequest;
  }
}