package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.seller.domain.SellerId;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

  private static final String A_TITLE = "A TITLE";
  private final Product A_PRODUCT = new ProductBuilder().withTitle(A_TITLE).build();

  @Test
  public void givenAProductWithATitleAndAnotherSameTitle_whenIsInTitle_thenShouldReturnTrue() {
    assertTrue(A_PRODUCT.isInTitle(A_TITLE));
  }

  @Test
  public void givenAProductWithATitleAndAnotherTitleWithAPartOfProductTitle_whenIsInTitle_thenShouldReturnTrue() {
    String aPartOfProductTitle = "TLE";

    assertTrue(A_PRODUCT.isInTitle(aPartOfProductTitle));
  }

  @Test
  public void givenAProductWithATitleAndAnotherTitleWithAPartOfProductTitleInDifferentCasing_whenIsInTitle_thenShouldReturnTrue() {
    String aPartOfProductTitle = "A tiT";

    assertTrue(A_PRODUCT.isInTitle(aPartOfProductTitle));
  }

  @Test
  public void givenAProductWithATitleAndAnotherDifferentTitle_whenIsInTitle_thenShouldReturnFalse() {
    String aDifferentTitle = "Marin";

    assertFalse(A_PRODUCT.isInTitle(aDifferentTitle));
  }

  @Test
  public void givenAProductWithASellerIdAndAnIdenticalSellerId_whenHasSameSellerId_thenShouldReturnTrue() {
    SellerId aSellerId = new SellerId();
    Product aProduct = new ProductBuilder().withSellerId(aSellerId).build();

    assertTrue(aProduct.hasSameSellerId(aSellerId));
  }

  @Test
  public void givenAProductWithASellerIdAndADifferentSellerId_whenHasSameSellerId_thenShouldReturnTrue() {
    SellerId aSellerId = new SellerId();
    SellerId aDifferentSellerId = new SellerId();
    Product aProduct = new ProductBuilder().withSellerId(aSellerId).build();

    assertFalse(aProduct.hasSameSellerId(aDifferentSellerId));
  }
}
