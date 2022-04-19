package ulaval.glo2003.util;

import java.time.LocalDate;
import java.time.Period;

public class AgeVerificator {

  public static boolean isYoungerThanAge(LocalDate date, int age) {
    return Period.between(date, LocalDate.now()).getYears() < age;
  }
}
