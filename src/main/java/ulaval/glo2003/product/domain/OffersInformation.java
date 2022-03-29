package ulaval.glo2003.product.domain;

import java.util.List;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OffersInformation that = (OffersInformation) o;
    return offersSummary.equals(that.offersSummary) && offers.equals(that.offers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offersSummary, offers);
  }
}
