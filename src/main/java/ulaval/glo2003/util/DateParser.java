package ulaval.glo2003.util;

import ulaval.glo2003.util.exceptions.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {
  private static final String DATE_FORMAT = "yyyy-MM-dd";
  private static final DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

  public static LocalDate format(String date) throws InvalidDateFormatException {
    try {
      return LocalDate.parse(date, localDateFormatter);
    } catch (Exception e) {
      throw new InvalidDateFormatException();
    }
  }

  public static LocalDateTime formatLocalDateTime(String date) throws InvalidDateFormatException {
    try {
      return LocalDateTime.parse(date);
    } catch (Exception e) {
      throw new InvalidDateFormatException();
    }
  }
}
