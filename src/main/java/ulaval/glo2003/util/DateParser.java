package ulaval.glo2003.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
  private static final String DATE_FORMAT = "dd/MM/yyyy";
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

  public static LocalDate format(String date) {
    return LocalDate.parse(date, formatter);
  }
}
