package ulaval.glo2003.util;

import ulaval.glo2003.util.exceptions.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
  private static final String DATE_FORMAT = "yyyy-MM-dd";
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

  public static LocalDate format(String date) throws InvalidDateFormatException {
    try {
      return LocalDate.parse(date, formatter);
    } catch (Exception e) {
      throw new InvalidDateFormatException();
    }
  }
}
