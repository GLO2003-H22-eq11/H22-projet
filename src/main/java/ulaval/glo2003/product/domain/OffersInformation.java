package ulaval.glo2003.product.domain;

import java.util.List;

public class OffersInformation {
  private final OffersSummary offersSummary;
  private final List<Offer> offers;

  public OffersInformation(OffersSummary offersSummary, List<Offer> offers) {
    this.offersSummary = offersSummary;
    this.offers = offers;
  }

  public Double getMin() {
    return this.offersSummary.getMinAmount();
  }

  public Double getMaxDoubleValue() {
    return this.offersSummary.getMaxAmount();
  }

  public Double getMean() {
    return this.offersSummary.getMeanAmount();
  }

  public Integer getCount() {
    return this.offersSummary.getCount();
  }

  public List<Offer> getOffers() {
    return this.offers;
  }
}
