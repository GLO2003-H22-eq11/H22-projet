package ulaval.glo2003.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class DateParserTest {

  @Test
  public void givenAStringDateIncorrectFormat_whenFormat_thenShouldFormatInLocalDate() {
    String aDate = "10/08/2002";
    LocalDate expectedDate = LocalDate.of(2002, 8, 10);

    LocalDate actualDate = DateParser.format(aDate);

    assertEquals(expectedDate, actualDate);
  }

  @Test
  public void givenAStringDateInIncorrectFormat_whenFormat_thenShouldThrowDateTimeParseException() {
    String anIncorrectDate = "10-08-2002";

    assertThrows(DateTimeParseException.class, () -> DateParser.format(anIncorrectDate));
  }

  @Test
  public void givenAStringDateWithIncorrectDayNumber_whenFormat_thenShouldThrowDateTimeParseException() {
    String anIncorrectDate = "40/08/2002";

    assertThrows(DateTimeParseException.class, () -> DateParser.format(anIncorrectDate));
  }

  @Test
  public void givenAStringDateWithIncorrectMonthNumber_whenFormat_thenShouldThrowDateTimeParseException() {
    String anIncorrectDate = "20/13/2002";

    assertThrows(DateTimeParseException.class, () -> DateParser.format(anIncorrectDate));
  }
}