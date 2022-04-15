package ulaval.glo2003.offer.api.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
  private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
          Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{1,6}$", Pattern.CASE_INSENSITIVE);

  public static boolean validate(String email) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    return matcher.find();
  }
}
