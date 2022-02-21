package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {
  private static final int A_HIGH_VALUE = 500;
  private static final int A_LOW_VALUE = 1;
  private final Amount A_HIGH_AMOUNT = Amount.fromInt(A_HIGH_VALUE);
  private final Amount A_LOW_AMOUNT = Amount.fromInt(A_LOW_VALUE);

  @Test
  public void givenAHighAmountAndASmallAmount_whenGettingIfHigherAmountIsHigher_thenShouldReturnTrue() {
    assertTrue(A_HIGH_AMOUNT.isHigher(A_LOW_AMOUNT));
  }

  @Test
  public void givenAHighAmountAndASmallAmount_whenGettingIfSmallerAmountIsHigher_thenShouldReturnFalse() {
    assertFalse(A_LOW_AMOUNT.isHigher(A_HIGH_AMOUNT));
  }

  @Test
  public void givenAnAmount_whenGettingIfAmountIsHigherThanSameAmount_thenShouldReturnFalse() {
    int aValue = 20;
    Amount anAmount = Amount.fromInt(aValue);

    assertFalse(anAmount.isHigher(anAmount));
  }
}