package ulaval.glo2003.product.domain;

public class OffersSummary {

  private Amount mean;
  private Integer count;
  private Amount min;
  private Amount max;

  public OffersSummary() {
    this.count = 0;
    this.min = null;
    this.max = null;
  }

  public Double getMeanAmount() {
    if (this.mean == null) {
      return null;
    }
    return this.mean.getDoubleValue();
  }

  public Integer getCount() {
    return this.count;
  }

  public Double getMaxAmount() {
    if(this.max == null) {
      return null;
    }
    return this.max.getDoubleValue();
  }

  public Double getMinAmount() {
    if(this.min == null) {
      return null;
    }
    return this.min.getDoubleValue();
  }

  public void addOfferAmount(Amount offerAmount) {
    if (this.max == null || offerAmount.isHigher(this.max)) {
      this.max = offerAmount;
    }

    if (this.min == null || this.min.isHigher(offerAmount)) {
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
