package ulaval.glo2003.offer.api.response;

import java.util.Objects;

public class OffersSummaryResponse {
  public Double mean;
  public Integer count;

  public OffersSummaryResponse() {
  }

  public OffersSummaryResponse(Double mean, Integer count) {
    this.mean = mean;
    this.count = count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OffersSummaryResponse that = (OffersSummaryResponse) o;
    return Objects.equals(mean, that.mean) && count.equals(that.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mean, count);
  }
}
