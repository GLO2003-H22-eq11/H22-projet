package ulaval.glo2003.products.domain;

import java.math.BigDecimal;

public final class Amount {
  private final BigDecimal dollarAmount;

  public Amount(BigDecimal amount) {
    this.dollarAmount = amount;
  }

  public int getAmount() {
    return this.dollarAmount.intValue();
  }
}
