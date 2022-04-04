package ulaval.glo2003.offer.api.validator;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.offer.api.validator.PhoneNumberValidator;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberValidatorTest {

  @Test
  public void givenAValidPhoneNumberString_whenValidate_thenShouldReturnTrue() {
    String aValidPhoneNumber = "14181234567";

    assertTrue(PhoneNumberValidator.validate(aValidPhoneNumber));
  }

  @Test
  public void givenAValidPhoneNumberStringWithOnlyZeros_whenValidate_thenShouldReturnTrue() {
    String aValidPhoneNumber = "00000000000";

    assertTrue(PhoneNumberValidator.validate(aValidPhoneNumber));
  }

  @Test
  public void givenAInvalidPhoneNumberStringWithDashes_whenValidate_thenShouldReturnFalse() {
    String aValidPhoneNumber = "1-418-123-4567";

    assertFalse(PhoneNumberValidator.validate(aValidPhoneNumber));
  }

  @Test
  public void givenAnInvalidPhoneNumberStringWithAMissingNumber_whenValidate_thenShouldReturnFalse() {
    String anInvalidPhoneNumber = "4181234567";

    assertFalse(PhoneNumberValidator.validate(anInvalidPhoneNumber));
  }

  @Test
  public void givenAnInvalidPhoneNumberStringWithOneTooManyNumber_whenValidate_thenShouldReturnFalse() {
    String anInvalidPhoneNumber = "141812345678";

    assertFalse(PhoneNumberValidator.validate(anInvalidPhoneNumber));
  }
}
