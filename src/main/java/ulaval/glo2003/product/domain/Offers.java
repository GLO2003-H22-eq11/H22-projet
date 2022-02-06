package ulaval.glo2003.product.domain;

public class Offers {
  private final Amount mean;
  private final int count;

  public Offers(Amount mean, int count) {
    this.mean = mean;
    this.count = count;
  }

  public int getMeanAmount() {
    return this.mean.getAmount();
  }

  public int getCount() {
    return this.count;
  }
}
