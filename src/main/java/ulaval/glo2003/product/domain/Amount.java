package ulaval.glo2003.product.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Amount {
  private final BigDecimal dollarAmount;

  private Amount(BigDecimal amount) {
    this.dollarAmount = amount;
  }

  public static Amount fromInt(int value) {
    return new Amount(BigDecimal.valueOf(value));
  }

  public BigDecimal getAmount() {
    return this.dollarAmount;
  }

  public int getIntValue() {
    return this.dollarAmount.intValue();
  }

  public boolean isHigher(Amount amount) {
    return this.dollarAmount.intValue() > amount.getAmount().intValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Amount amount = (Amount) o;
    return dollarAmount.equals(amount.dollarAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dollarAmount);
  }
}
