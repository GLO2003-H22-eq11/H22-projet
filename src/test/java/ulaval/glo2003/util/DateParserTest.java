package ulaval.glo2003.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class DateParserTest {

  @Test
  public void givenAStringDateInCorrectFormat_whenFormat_thenShouldFormatInLocalDate() {
    String aDate = "2002-10-08";
    LocalDate expectedDate = LocalDate.of(2002, 10, 8);

    LocalDate actualDate = DateParser.format(aDate);

    assertEquals(expectedDate, actualDate);
  }

  @Test
  public void givenAStringDateInIncorrectFormat_whenFormat_thenShouldThrowDateTimeParseException() {
    String anIncorrectDate = "2002/10/08";

    assertThrows(DateTimeParseException.class, () -> DateParser.format(anIncorrectDate));
  }

  @Test
  public void givenAStringDateWithIncorrectDayNumber_whenFormat_thenShouldThrowDateTimeParseException() {
    String anIncorrectDate = "2002-08-40";

    assertThrows(DateTimeParseException.class, () -> DateParser.format(anIncorrectDate));
  }

  @Test
  public void givenAStringDateWithIncorrectMonthNumber_whenFormat_thenShouldThrowDateTimeParseException() {
    String anIncorrectDate = "2002-20-13";

    assertThrows(DateTimeParseException.class, () -> DateParser.format(anIncorrectDate));
  }
}