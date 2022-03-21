package ulaval.glo2003.main.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

  @Test
  public void givenAValidEmailString_whenValidate_thenShouldReturnTrue() {
    String aValidEmailString = "bonjourno@email.ca";

    assertTrue(EmailValidator.validate(aValidEmailString));
  }

  @Test
  public void givenAnInvalidEmailStringWithNoDomain_whenValidate_thenShouldReturnFalse() {
    String anInValidEmailString = "bonjourno@email";

    assertFalse(EmailValidator.validate(anInValidEmailString));
  }

  @Test
  public void givenAnInvalidEmailStringWithNoAt_whenValidate_thenShouldReturnFalse() {
    String anInValidEmailString = "bonjournoemail.ca";

    assertFalse(EmailValidator.validate(anInValidEmailString));
  }


}