package ulaval.glo2003.product.domain;

import java.math.BigDecimal;

public class AmountFactory {

  public Amount create(int amount) {
    BigDecimal bigDecimal = BigDecimal.valueOf(amount);
    return new Amount(bigDecimal);
  }
}
