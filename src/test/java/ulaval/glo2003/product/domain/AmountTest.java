package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.main.domain.Amount;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {
  private static final Double A_HIGH_VALUE = 500.0;
  private static final Double A_LOW_VALUE = 1.0;
  private static final Double A_VALUE = 20.0;
  private final Amount A_HIGH_AMOUNT = Amount.fromDouble(A_HIGH_VALUE);
  private final Amount A_LOW_AMOUNT = Amount.fromDouble(A_LOW_VALUE);
  private final Amount AN_AMOUNT = Amount.fromDouble(A_VALUE);

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
    assertFalse(AN_AMOUNT.isHigher(AN_AMOUNT));
  }

  @Test
  public void givenAHighAmountAndASmallAmount_whenGettingIfHigherAmountIsHigherOrEqual_thenShouldReturnTrue() {
    assertTrue(A_HIGH_AMOUNT.isHigherOrEqual(A_LOW_AMOUNT));
  }

  @Test
  public void givenAHighAmountAndASmallAmount_whenGettingIfSmallerAmountIsHigherOrEqual_thenShouldReturnFalse() {
    assertFalse(A_LOW_AMOUNT.isHigher(A_HIGH_AMOUNT));
  }

  @Test
  public void givenAnAmount_whenGettingIfAmountIsHigherOrEqualThanSameAmount_thenShouldReturnTrue() {
    assertFalse(AN_AMOUNT.isHigher(AN_AMOUNT));
  }

  @Test
  public void givenAnAmountAndAnAmountToAdd_whenAdd_thenShouldAddAmount() {
    Amount amountToAdd = Amount.fromDouble(5.5);

    Amount expectedAmount = Amount.fromDouble(25.5);
    Amount resultAmount = AN_AMOUNT.add(amountToAdd);

    assertEquals(expectedAmount, resultAmount);
  }

  @Test
  public void givenAnAmountAndANumberToMultiplyBy_whenMultiply_thenShouldMultiply() {
    int aNumberToMultiplyBy = 8;

    Amount expectedAmount = Amount.fromDouble(160.0);
    Amount resultAmount = AN_AMOUNT.multiply(aNumberToMultiplyBy);

    assertEquals(expectedAmount, resultAmount);
  }

  @Test
  public void givenAnAmountAndANumberToDivideBy_whenDivide_thenShouldDivide() {
    int aNumberToDivideBy = 5;

    Amount expectedAmount = Amount.fromDouble(4.0);
    Amount resultAmount = AN_AMOUNT.divide(aNumberToDivideBy);

    assertEquals(expectedAmount, resultAmount);
  }
}