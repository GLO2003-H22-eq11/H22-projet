package ulaval.glo2003.offer.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.offer.domain.OffersSummary;

import static org.junit.jupiter.api.Assertions.*;

class OffersSummaryTest {

  private static final Double A_VALUE = 20.0;
  private static final Double ANOTHER_VALUE = 40.0;
  private static final Amount AN_AMOUNT = Amount.fromDouble(A_VALUE);
  private static final Amount ANOTHER_AMOUNT = Amount.fromDouble(ANOTHER_VALUE);

  private final OffersSummary offersSummary = new OffersSummary();
  private final OffersSummary offersSummaryWithNullMeanMinMax = new OffersSummary();

  @BeforeEach
  public void setUp() {
    offersSummary.addOfferAmount(AN_AMOUNT);
  }

  @Test
  public void givenAnOffersSummaryWithAnOffer_whenAddOfferAmount_thenShouldAdjustMeanAndCount() {
    int expectedCount = 2;
    Double expectedMeanAmount = 30.0;

    offersSummary.addOfferAmount(ANOTHER_AMOUNT);

    int actualCount = offersSummary.getCount();
    Double actualMeanAmount = offersSummary.getMeanAmount();

    assertEquals(expectedCount, actualCount);
    assertEquals(expectedMeanAmount, actualMeanAmount);
  }

  @Test
  public void givenAnOffersSummaryWithAnOffer_whenAddOfferAmount_thenShouldAdjustMinAndMax() {
    offersSummary.addOfferAmount(ANOTHER_AMOUNT);

    Double actualMin = offersSummary.getMinAmount();
    Double actualMax = offersSummary.getMaxAmount();

    assertEquals(A_VALUE, actualMin);
    assertEquals(ANOTHER_VALUE, actualMax);
  }

  @Test
  public void givenAnOffersSummaryWithANullMeanValueAndAnOffer_whenAddOfferAmount_thenShouldAdjustOffersMeanAndCount() {
    int expectedCount = 1;

    offersSummaryWithNullMeanMinMax.addOfferAmount(AN_AMOUNT);

    int actualCount = offersSummaryWithNullMeanMinMax.getCount();
    Double actualMeanAmount = offersSummaryWithNullMeanMinMax.getMeanAmount();

    assertEquals(expectedCount, actualCount);
    assertEquals(A_VALUE, actualMeanAmount);
  }
}