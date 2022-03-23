package ulaval.glo2003.product.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Amount {
  private final BigDecimal dollarAmount;

  private Amount(BigDecimal amount) {
    this.dollarAmount = amount;
  }

  public static Amount fromDouble(Double value) {
    return new Amount(BigDecimal.valueOf(value));
  }

  public BigDecimal getAmount() {
    return this.dollarAmount;
  }

  public Double getDoubleValue() {
    return this.dollarAmount.doubleValue();
  }

  public boolean isHigher(Amount amount) {
    return this.dollarAmount.intValue() > amount.getAmount().intValue();
  }

  public boolean isHigherOrEqual(Amount amount) {
    return this.dollarAmount.intValue() >= amount.getAmount().intValue();
  }

  public Amount add(Amount amount) {
    BigDecimal newAmount = dollarAmount.add(amount.getAmount());
    return new Amount(newAmount);
  }

  public Amount multiply(int number) {
    BigDecimal newAmount = dollarAmount.multiply(BigDecimal.valueOf(number));
    return new Amount(newAmount);
  }

  public Amount divide(int number) {
    BigDecimal newAmount = dollarAmount.divide(BigDecimal.valueOf(number));
    return new Amount(newAmount);
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
