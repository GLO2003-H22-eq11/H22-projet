package ulaval.glo2003.seller.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

  @Test
  public void givenASellerWithAgeOf18_whenGettingIsMajor_thenShouldNotThrow() {
    LocalDate aBirthDateOfA18YearsOld = LocalDate.of(2004, 1, 2);
    Seller seller = new SellerBuilder().withBirthDate(aBirthDateOfA18YearsOld).build();

    assertDoesNotThrow(seller::verifyIfMajor);
  }

  @Test
  public void givenASellerWithAgeOf22_whenGettingIsMajor_thenShouldNotThrow() {
    LocalDate aBirthDateOfA22YearsOld = LocalDate.of(2000, 1, 2);
    Seller seller = new SellerBuilder().withBirthDate(aBirthDateOfA22YearsOld).build();

    assertDoesNotThrow(seller::verifyIfMajor);
  }

  @Test
  public void givenASellerWithAgeOf17_whenGettingIsMajor_thenShouldThrow() {
    LocalDate aBirthDateOfA17YearsOld = LocalDate.of(2005, 1, 2);
    Seller seller = new SellerBuilder().withBirthDate(aBirthDateOfA17YearsOld).build();

    assertThrows(SellerIsMinorException.class, seller::verifyIfMajor);
  }
}