package ulaval.glo2003.product.api.validator;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.api.validator.PhoneNumberValidator;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidatorTest {

  @Test
  public void givenAValidPhoneNumberString_whenValidate_thenShouldReturnTrue() {
    String aValidPhoneNumber = "4181234567";

    assertTrue(PhoneNumberValidator.validate(aValidPhoneNumber));
  }

  @Test
  public void givenAValidPhoneNumberStringWithDashes_whenValidate_thenShouldReturnTrue() {
    String aValidPhoneNumber = "418-123-4567";

    assertTrue(PhoneNumberValidator.validate(aValidPhoneNumber));
  }

  @Test
  public void givenAnInvalidPhoneNumberStringWithAMissingNumber_whenValidate_thenShouldReturnFalse() {
    String anInvalidPhoneNumber = "418123456";

    assertFalse(PhoneNumberValidator.validate(anInvalidPhoneNumber));
  }

  @Test
  public void givenAnInvalidPhoneNumberWithDashesAndAMissingNumberString_whenValidate_thenShouldReturnFalse() {
    String anInvalidPhoneNumber = "418-123-456";

    assertFalse(PhoneNumberValidator.validate(anInvalidPhoneNumber));
  }
}
