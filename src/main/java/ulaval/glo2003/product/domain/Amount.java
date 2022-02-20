package ulaval.glo2003.product.domain;

import java.math.BigDecimal;

public final class Amount {
  private final BigDecimal dollarAmount;

  public Amount(BigDecimal amount) {
    this.dollarAmount = amount;
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
}
