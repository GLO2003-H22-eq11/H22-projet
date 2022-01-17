package ulaval.glo2003.seller.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {
  private static final String A_NAME = "Bobby";
  private static final SellerId A_SELLER_ID = new SellerId();
  private static final LocalDateTime A_CREATION_DATE = LocalDateTime.now();
  private static final String A_BIO = "Bobby aime les chiens";

  @Test
  public void givenASellerWithAgeOf18_whenGettingIsMajor_thenShouldReturnTrue() {
    LocalDate aBirthDateOfA18YearsOld = LocalDate.of(2004, 1, 2);

    Seller seller = new Seller(A_SELLER_ID, A_NAME, A_BIO, aBirthDateOfA18YearsOld, A_CREATION_DATE);

    assertTrue(seller.isMajor());
  }

  @Test
  public void givenASellerWithAgeOf22_whenGettingIsMajor_thenShouldReturnTrue() {
    LocalDate aBirthDateOfA22YearsOld = LocalDate.of(2000, 1, 2);
    Seller seller = new Seller(A_SELLER_ID, A_NAME, A_BIO, aBirthDateOfA22YearsOld, A_CREATION_DATE);

    assertTrue(seller.isMajor());
  }

  @Test
  public void givenASellerWithAgeOf17_whenGettingIsMajor_thenShouldReturnFalse() {
    LocalDate aBirthDateOfA17YearsOld = LocalDate.of(2005, 1, 2);
    Seller seller = new Seller(A_SELLER_ID, A_NAME, A_BIO, aBirthDateOfA17YearsOld, A_CREATION_DATE);

    assertFalse(seller.isMajor());
  }
}