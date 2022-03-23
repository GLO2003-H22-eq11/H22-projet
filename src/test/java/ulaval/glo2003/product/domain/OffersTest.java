package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OffersTest {

  private static final Double A_VALUE = 20.0;
  private static final Amount AN_AMOUNT = Amount.fromDouble(A_VALUE);

  private final Offers offers = new Offers();
  private final Offers offersWithNullMean = new Offers();

  @BeforeEach
  public void setUp() {
    offers.addOfferAmount(AN_AMOUNT);
  }

  @Test
  public void givenAnOffersWithANullMean_whenGetMeanAmount_thenShouldReturnNull() {
    assertNull(offersWithNullMean.getMeanAmount());
  }

  @Test
  public void givenAnOffersWithANonNullMean_whenGetMeanAmount_thenShouldReturnMeanValue() {
    assertEquals(A_VALUE, offers.getMeanAmount());
  }

  @Test
  public void givenAnOffersWithTwoOffers_whenAddOfferAmount_thenShouldAdjustOffersMeanAndCount() {
    Amount anAmount = Amount.fromDouble(40.0);
    int expectedCount = 2;
    Double expectedMeanAmount = 30.0;

    offers.addOfferAmount(anAmount);

    int actualCount = offers.getCount();
    Double actualMeanAmount = offers.getMeanAmount();

    assertEquals(expectedCount, actualCount);
    assertEquals(expectedMeanAmount, actualMeanAmount);
  }

  @Test
  public void givenAnOffersWithANullMeanValueAndAnOffer_whenAddOfferAmount_thenShouldAdjustOffersMeanAndCount() {
    int expectedCount = 1;

    offersWithNullMean.addOfferAmount(AN_AMOUNT);

    int actualCount = offersWithNullMean.getCount();
    Double actualMeanAmount = offersWithNullMean.getMeanAmount();

    assertEquals(expectedCount, actualCount);
    assertEquals(A_VALUE, actualMeanAmount);
  }
}