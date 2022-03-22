package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.main.domain.Email;
import ulaval.glo2003.main.domain.PhoneNumber;

import static org.junit.jupiter.api.Assertions.*;

class OffersTest {

  private static final String A_NAME = "Offer name";
  private static final Email AN_EMAIL = new Email("allo@email.ca");
  private static final PhoneNumber A_PHONE_NUMBER = new PhoneNumber("4181234567");
  private static final String A_MESSAGE = "jkdbfkdfjkbdfbfadfbadbfkjsdfbjk";
  private static final Double A_VALUE = 20.0;
  private static final Amount AN_AMOUNT = Amount.fromDouble(A_VALUE);
  private static final ProductId A_PRODUCT_ID = new ProductId();
  private static final Offer AN_OFFER = new Offer(A_PRODUCT_ID, A_NAME, AN_EMAIL, A_PHONE_NUMBER, AN_AMOUNT, A_MESSAGE);

  private final Offers offers = new Offers();
  private final Offers offersWithNullMean = new Offers();

  @BeforeEach
  public void setUp() {
    offers.addOffer(AN_OFFER);
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
  public void givenAnOffersWithTwoOffers_whenAddOffer_thenShouldAdjustOffersMeanAndCount() {
    Amount anAmount = Amount.fromDouble(40.0);
    Offer anotherOffer = new Offer(A_PRODUCT_ID, A_NAME, AN_EMAIL, A_PHONE_NUMBER, anAmount, A_MESSAGE);
    int expectedCount = 2;
    Double expectedMeanAmount = 30.0;

    offers.addOffer(anotherOffer);

    int actualCount = offers.getCount();
    Double actualMeanAmount = offers.getMeanAmount();

    assertEquals(expectedCount, actualCount);
    assertEquals(expectedMeanAmount, actualMeanAmount);
  }

  @Test
  public void givenAnOffersWithANullMeanValueAndAnOffer_whenAddOffer_thenShouldAdjustOffersMeanAndCount() {
    int expectedCount = 1;

    offersWithNullMean.addOffer(AN_OFFER);

    int actualCount = offersWithNullMean.getCount();
    Double actualMeanAmount = offersWithNullMean.getMeanAmount();

    assertEquals(expectedCount, actualCount);
    assertEquals(A_VALUE, actualMeanAmount);
  }
}