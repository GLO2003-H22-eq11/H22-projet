package ulaval.glo2003.product.api;

import ulaval.glo2003.product.api.response.OffersInformationResponse;

import java.util.List;

public class ProductWithOfferResponse {
  public String id;
  public String title;
  public String description;
  public double suggestedPrice;
  public List<String> categories;
  public OffersInformationResponse offersInformationResponse;

  public ProductWithOfferResponse(String id, String title, String description, double suggestedPrice,
                                  List<String> categories, OffersInformationResponse offersInformationResponse) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.categories = categories;
    this.offersInformationResponse = offersInformationResponse;
  }
}
