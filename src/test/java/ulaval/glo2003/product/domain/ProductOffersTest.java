package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductOffersTest {

  private static final Double A_VALUE = 20.0;
  private static final Amount AN_AMOUNT = Amount.fromDouble(A_VALUE);

  private final OffersSummary offersSummary = new OffersSummary();
  private final OffersSummary offersSummaryWithNullMean = new OffersSummary();

  @BeforeEach
  public void setUp() {
    offersSummary.addOfferAmount(AN_AMOUNT);
  }

  @Test
  public void givenAnOffersWithANullMean_whenGetMeanAmount_thenShouldReturnNull() {
    assertNull(offersSummaryWithNullMean.getMeanAmount());
  }

  @Test
  public void givenAnOffersWithANonNullMean_whenGetMeanAmount_thenShouldReturnMeanValue() {
    assertEquals(A_VALUE, offersSummary.getMeanAmount());
  }

  @Test
  public void givenAnOffersWithTwoOffers_whenAddOfferAmount_thenShouldAdjustOffersMeanAndCount() {
    Amount anAmount = Amount.fromDouble(40.0);
    int expectedCount = 2;
    Double expectedMeanAmount = 30.0;

    offersSummary.addOfferAmount(anAmount);

    int actualCount = offersSummary.getCount();
    Double actualMeanAmount = offersSummary.getMeanAmount();

    assertEquals(expectedCount, actualCount);
    assertEquals(expectedMeanAmount, actualMeanAmount);
  }

  @Test
  public void givenAnOffersWithANullMeanValueAndAnOffer_whenAddOfferAmount_thenShouldAdjustOffersMeanAndCount() {
    int expectedCount = 1;

    offersSummaryWithNullMean.addOfferAmount(AN_AMOUNT);

    int actualCount = offersSummaryWithNullMean.getCount();
    Double actualMeanAmount = offersSummaryWithNullMean.getMeanAmount();

    assertEquals(expectedCount, actualCount);
    assertEquals(A_VALUE, actualMeanAmount);
  }
}