package ulaval.glo2003.product.domain;

public class OffersSummary {
  private Amount mean;
  private Integer count;
  private Amount min;
  private Amount max;

  public OffersSummary() {
    this.count = 0;
    this.min = Amount.fromDouble(0.0);
    this.max = Amount.fromDouble(0.0);
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

  public Amount getMax() {
    return this.max;
  }

  public Amount getMin() {
    return this.min;
  }

  public void addOfferAmount(Amount offerAmount) {

    if (offerAmount.isHigher(this.max)) {
      this.max = offerAmount;
    }

    if (this.min.isHigher(offerAmount)) {
      this.min = offerAmount;
    }

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
