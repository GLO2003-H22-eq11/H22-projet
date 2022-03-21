package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.main.domain.Amount;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {
  private static final Double A_HIGH_VALUE = 500.0;
  private static final Double A_LOW_VALUE = 1.0;
  private final Amount A_HIGH_AMOUNT = Amount.fromDouble(A_HIGH_VALUE);
  private final Amount A_LOW_AMOUNT = Amount.fromDouble(A_LOW_VALUE);

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
    Double aValue = 20.0;
    Amount anAmount = Amount.fromDouble(aValue);

    assertFalse(anAmount.isHigher(anAmount));
  }
}