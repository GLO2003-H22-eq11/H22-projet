package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.exceptions.InvalidOfferPriceException;
import ulaval.glo2003.seller.domain.SellerId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class ProductTest {

  private static final String A_TITLE = "A TITLE";
  private static final Amount A_PRICE = Amount.fromDouble(20.0);
  private static final OffersSummary PRODUCT_OFFERS = spy(new OffersSummary());

  private final Product A_PRODUCT_WITH_A_TITLE = new ProductBuilder().withTitle(A_TITLE).build();
  private final Product A_PRODUCT_WITH_A_PRICE = new ProductBuilder().withAmount(A_PRICE).withOffers(PRODUCT_OFFERS).build();

  @Test
  public void givenAProductWithATitleAndAnotherSameTitle_whenIsInTitle_thenShouldReturnTrue() {
    assertTrue(A_PRODUCT_WITH_A_TITLE.isInTitle(A_TITLE));
  }

  @Test
  public void givenAProductWithATitleAndAnotherTitleWithAPartOfProductTitle_whenIsInTitle_thenShouldReturnTrue() {
    String aPartOfProductTitle = "TLE";

    assertTrue(A_PRODUCT_WITH_A_TITLE.isInTitle(aPartOfProductTitle));
  }

  @Test
  public void givenAProductWithATitleAndAnotherTitleWithAPartOfProductTitleInDifferentCasing_whenIsInTitle_thenShouldReturnTrue() {
    String aPartOfProductTitle = "A tiT";

    assertTrue(A_PRODUCT_WITH_A_TITLE.isInTitle(aPartOfProductTitle));
  }

  @Test
  public void givenAProductWithATitleAndAnotherDifferentTitle_whenIsInTitle_thenShouldReturnFalse() {
    String aDifferentTitle = "Marin";

    assertFalse(A_PRODUCT_WITH_A_TITLE.isInTitle(aDifferentTitle));
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

  @Test
  public void givenAnOfferWithSamePriceAsProduct_whenAddOfferAmount_thenShouldAddOffer() throws InvalidOfferPriceException {
    A_PRODUCT_WITH_A_PRICE.addOfferAmount(A_PRICE);

    verify(PRODUCT_OFFERS).addOfferAmount(A_PRICE);
  }

  @Test
  public void givenAnOfferWithPriceHigherThanProduct_whenAddOfferAmount_thenShouldAddOffer() throws InvalidOfferPriceException {
    Amount aHigherPrice = Amount.fromDouble(250.0);

    A_PRODUCT_WITH_A_PRICE.addOfferAmount(aHigherPrice);

    verify(PRODUCT_OFFERS).addOfferAmount(aHigherPrice);
  }

  @Test
  public void givenAnOfferWithPriceLowerThanProduct_whenAddOfferAmount_thenShouldThrowInvalidOfferPriceException() {
    Amount aLowerPrice = Amount.fromDouble(2.0);

    assertThrows(InvalidOfferPriceException.class, () -> A_PRODUCT_WITH_A_PRICE.addOfferAmount(aLowerPrice));
  }
}
