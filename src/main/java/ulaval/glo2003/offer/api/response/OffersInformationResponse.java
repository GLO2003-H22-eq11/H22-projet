package ulaval.glo2003.offer.api.response;

import java.util.List;

public class OffersInformationResponse {
  public Double min;
  public Double max;
  public Double mean;
  public Integer count;
  public List<OfferResponse> items;

  public OffersInformationResponse() {
  }

  public OffersInformationResponse(Double min, Double max, Double mean, Integer count, List<OfferResponse> items) {
    this.min = min;
    this.max = max;
    this.mean = mean;
    this.count = count;
    this.items = items;
  }
}
