package ulaval.glo2003.product.api.response;

import java.util.List;

public class ProductWithOffersResponse {
  public String id;
  public String title;
  public String description;
  public Double suggestedPrice;
  public String createdAt;
  public List<String> categories;
  public OffersInformationResponse offers;

  public ProductWithOffersResponse(String id, String title, String description, Double suggestedPrice,
                                   List<String> categories, OffersInformationResponse offersInformationResponse,
                                   String createdAt) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.categories = categories;
    this.offers = offersInformationResponse;
    this.createdAt = createdAt;
  }
}
