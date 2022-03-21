package ulaval.glo2003.product.domain;

import ulaval.glo2003.main.domain.Amount;

public class Offers {
  private Amount mean;
  private int count;

  public Offers(Amount mean, int count) {
    this.mean = mean;
    this.count = count;
  }

  public int getMeanAmount() {
    return this.mean.getIntValue();
  }

  public int getCount() {
    return this.count;
  }

  public void addOffer(Offer offer) {
    if (this.mean != null) {
      Amount totalAMountOfOffers = this.mean.multiply(this.count).add(offer.getAmount());
      this.count += 1;
      this.mean = totalAMountOfOffers.divide(this.count);
    } else {
      this.mean = offer.getAmount();
      this.count += 1;
    }
  }
}
