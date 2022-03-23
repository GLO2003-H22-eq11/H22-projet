package ulaval.glo2003.product.domain;

public class Offers {
  private Amount mean;
  private Integer count;

  public Offers() {
    this.count = 0;
  }

  public Double getMeanAmount() {
    if (this.mean == null) {
      return null;
    }
    return this.mean.getDoubleValue();
  }

  public int getCount() {
    return this.count;
  }

  public void addOfferAmount(Amount offerAmount) {
    if (this.mean != null) {
      Amount totalAMountOfOffers = this.mean.multiply(this.count).add(offerAmount);
      this.count += 1;
      this.mean = totalAMountOfOffers.divide(this.count);
    } else {
      this.mean = offerAmount;
      this.count += 1;
    }
  }
}
