package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.product.domain.exceptions.InvalidOfferPriceException;
import ulaval.glo2003.seller.domain.SellerId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class ProductTest {

  private static final String A_TITLE = "A TITLE";
  private static final Amount A_PRICE = Amount.fromDouble(20.0);
  private static final Offers OFFERS = spy(new Offers());

  private final Product A_PRODUCT_WITH_A_TITLE = new ProductBuilder().withTitle(A_TITLE).build();
  private final Product A_PRODUCT_WITH_A_PRICE = new ProductBuilder().withAmount(A_PRICE).withOffers(OFFERS).build();

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
  public void givenAnOfferWithSamePriceAsProduct_whenAddOffer_thenShouldAddOffer() throws InvalidOfferPriceException {
    Offer anOfferWithSamePrice = new OfferBuilder().withAmount(A_PRICE).build();

    A_PRODUCT_WITH_A_PRICE.addOffer(anOfferWithSamePrice);

    verify(OFFERS).addOffer(anOfferWithSamePrice);
  }

  @Test
  public void givenAnOfferWithPriceHigherThanProduct_whenAddOffer_thenShouldAddOffer() throws InvalidOfferPriceException {
    Amount aHigherPrice = Amount.fromDouble(250.0);
    Offer anOfferWithHigherPrice = new OfferBuilder().withAmount(aHigherPrice).build();

    A_PRODUCT_WITH_A_PRICE.addOffer(anOfferWithHigherPrice);

    verify(OFFERS).addOffer(anOfferWithHigherPrice);
  }

  @Test
  public void givenAnOfferWithPriceLowerThanProduct_whenAddOffer_thenShouldThrowInvalidOfferPriceException() throws InvalidOfferPriceException {
    Amount aLowerPrice = Amount.fromDouble(2.0);
    Offer anOfferWithHigherPrice = new OfferBuilder().withAmount(aLowerPrice).build();

    assertThrows(InvalidOfferPriceException.class, () -> A_PRODUCT_WITH_A_PRICE.addOffer(anOfferWithHigherPrice));
  }
}
