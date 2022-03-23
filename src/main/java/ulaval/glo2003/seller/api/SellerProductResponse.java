package ulaval.glo2003.seller.api;

import ulaval.glo2003.product.api.response.OffersResponse;

import java.util.List;

public class SellerProductResponse {
  public String id;
  public String createdAt;
  public String title;
  public String description;
  public Double suggestedPrice;
  public OffersResponse productOffers;
  public List<String> categories;

  public SellerProductResponse() {
  }

  public SellerProductResponse(
          String id,
          String createdAt,
          String title,
          String description,
          Double suggestedPrice,
          OffersResponse productOffers,
          List<String> categories
  ) {
    this.id = id;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.productOffers = productOffers;
    this.categories = categories;
  }
}
