package ulaval.glo2003.seller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.api.exceptions.InvalidSellerBiographyException;
import ulaval.glo2003.seller.api.exceptions.InvalidSellerBirthDateException;
import ulaval.glo2003.seller.api.exceptions.InvalidSellerNameException;
import ulaval.glo2003.seller.api.request.SellerRequest;
import ulaval.glo2003.seller.api.validator.SellerRequestValidator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SellerRequestValidatorTest {
  private static final String A_NAME = "Captain B";
  private static final String A_BIO = "I am the captain";
  private static final String A_BIRTH_DATE = "2000-01-02";

  private final SellerRequest A_VALID_SELLER_REQUEST = this.givenASellerRequest(A_NAME, A_BIO, A_BIRTH_DATE);

  @Mock
  private ConstraintsValidator constraintsValidator;

  private SellerRequestValidator sellerRequestValidator;

  @BeforeEach
  public void setUp() {
    this.sellerRequestValidator= new SellerRequestValidator(this.constraintsValidator);
  }

  @Test
  public void givenASellerRequest_whenValidate_thenShouldValidateWithConstraintsValidator() throws GenericException {
    this.sellerRequestValidator.validate(A_VALID_SELLER_REQUEST);

    verify(this.constraintsValidator).validate(A_VALID_SELLER_REQUEST);
  }

  @Test
  public void givenAValidSellerRequest_whenValidate_thenShouldNotThrow() {
    assertDoesNotThrow(() -> this.sellerRequestValidator.validate(A_VALID_SELLER_REQUEST));
  }

  @Test
  public void givenAnEmptyName_whenValidate_thenShouldThrowInvalidSellerNameException() {
    SellerRequest aSellerRequest = this.givenASellerRequest("", A_BIO, A_BIRTH_DATE);

    assertThrows(InvalidSellerNameException.class, () -> this.sellerRequestValidator.validate(aSellerRequest));
  }

  @Test
  public void givenANameWithSpacesOnly_whenValidate_thenShouldThrowInvalidSellerNameException() {
    SellerRequest aSellerRequest = this.givenASellerRequest("    ", A_BIO, A_BIRTH_DATE);

    assertThrows(InvalidSellerNameException.class, () -> this.sellerRequestValidator.validate(aSellerRequest));
  }

  @Test
  public void givenAnEmptyBio_whenValidate_thenShouldThrowInvalidSellerBiographyException() {
    SellerRequest aSellerRequest = this.givenASellerRequest(A_NAME, "", A_BIRTH_DATE);

    assertThrows(InvalidSellerBiographyException.class, () -> this.sellerRequestValidator.validate(aSellerRequest));
  }

  @Test
  public void givenABioWithSpacesOnly_whenValidate_thenShouldThrowInvalidSellerBiographyException() {
    SellerRequest aSellerRequest = this.givenASellerRequest(A_NAME, "     ", A_BIRTH_DATE);

    assertThrows(InvalidSellerBiographyException.class, () -> this.sellerRequestValidator.validate(aSellerRequest));
  }

  @Test
  public void givenABirthDateAfterTodayDate_whenValidate_thenShouldThrowInvalidSellerBirthdateException() {
    String anInvalidBirthDate = "2593-05-08";
    SellerRequest aSellerRequest = this.givenASellerRequest(A_NAME, A_BIO, anInvalidBirthDate);

    assertThrows(InvalidSellerBirthDateException.class, () -> this.sellerRequestValidator.validate(aSellerRequest));
  }

  private SellerRequest givenASellerRequest(String name, String bio, String birthDate) {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = name;
    sellerRequest.bio = bio;
    sellerRequest.birthDate = birthDate;

    return sellerRequest;
  }
}
