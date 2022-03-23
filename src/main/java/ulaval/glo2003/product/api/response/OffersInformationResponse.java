package ulaval.glo2003.product.api.response;

import java.util.List;

public class OffersInformationResponse {
  public double min;
  public double max;
  public double mean;
  public int count;
  public List<OfferResponse> items;

  public OffersInformationResponse(double min, double max, double mean, int count, List<OfferResponse> items) {
    this.min = min;
    this.max = max;
    this.mean = mean;
    this.count = count;
    this.items = items;
  }

}
