package ulaval.glo2003.seller.domain.factory;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.seller.domain.exceptions.SellerIsMinorException;

import static org.junit.jupiter.api.Assertions.*;

class SellerFactoryTest {
  private final SellerIdFactory sellerIdFactory = new SellerIdFactory();
  private final SellerFactory sellerFactory = new SellerFactory(sellerIdFactory);

  private static final String A_NAME = "PhilHockey99";
  private static final String A_BIO = "Peace sign and duck faces";
  private static final String A_MINOR_BIRTH_DATE = "2020-01-02";
  private static final String A_MAJOR_BIRTH_DATE = "1990-01-02";

  @Test
  public void givenAMinorBirthDate_whenAddSeller_thenShouldThrowSellerIsMinorException() {
    assertThrows(SellerIsMinorException.class, () ->  this.sellerFactory.create(A_NAME, A_BIO, A_MINOR_BIRTH_DATE));
  }

  @Test
  public void givenAMajorBirthDate_whenAddSeller_thenShouldNotThrow() {
    assertDoesNotThrow(() ->  this.sellerFactory.create(A_NAME, A_BIO, A_MAJOR_BIRTH_DATE));
  }
}