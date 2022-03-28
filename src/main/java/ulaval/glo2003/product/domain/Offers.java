package ulaval.glo2003.product.domain;

import java.util.Objects;

public class Offers {
  private Amount mean;
  private Integer count;

  public Offers() {
    this.count = 0;
  }

  public Offers(Amount mean, Integer count) {
    this.mean = mean;
    this.count = count;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Offers offers = (Offers) o;
    return Objects.equals(mean, offers.mean) && count.equals(offers.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mean, count);
  }
}
