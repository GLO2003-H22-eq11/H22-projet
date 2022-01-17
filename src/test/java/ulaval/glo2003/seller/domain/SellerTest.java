package ulaval.glo2003.seller.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {
  private static final String A_NAME = "Bobby";
  private static final String A_BIO = "Bobby aime les chiens";

  @Test
  public void givenASellerWithAgeOf18_whenGettingIsMajor_thenShouldReturnTrue() {
    LocalDate aBirthDateOfA18YearsOld = LocalDate.of(2004, 1, 2);
    Seller seller = new Seller(A_NAME, A_BIO, aBirthDateOfA18YearsOld);

    assertTrue(seller.isMajor());
  }

  @Test
  public void givenASellerWithAgeOf22_whenGettingIsMajor_thenShouldReturnTrue() {
    LocalDate aBirthDateOfA22YearsOld = LocalDate.of(2000, 1, 2);
    Seller seller = new Seller(A_NAME, A_BIO, aBirthDateOfA22YearsOld);

    assertTrue(seller.isMajor());
  }

  @Test
  public void givenASellerWithAgeOf17_whenGettingIsMajor_thenShouldReturnFalse() {
    LocalDate aBirthDateOfA17YearsOld = LocalDate.of(2005, 1, 2);
    Seller seller = new Seller(A_NAME, A_BIO, aBirthDateOfA17YearsOld);

    assertFalse(seller.isMajor());
  }
}