package ulaval.glo2003.product.api.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {
  private static final Pattern VALID_PHONE_NUMBER_REGEX =
          Pattern.compile("^\\d{11}$", Pattern.CASE_INSENSITIVE);

  public static boolean validate(String phoneNumber) {
    Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNumber);
    return matcher.find();
  }
}
