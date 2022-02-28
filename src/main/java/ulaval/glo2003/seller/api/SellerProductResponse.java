package ulaval.glo2003.seller.api;

import ulaval.glo2003.product.api.response.OffersResponse;

import java.util.List;

public class SellerProductResponse {
  public String id;
  public String createdAt;
  public String title;
  public String description;
  public int suggestedPrice;
  public OffersResponse offers;
  public List<String> categories;

  public SellerProductResponse() {
  }

  public SellerProductResponse(
          String id,
          String createdAt,
          String title,
          String description,
          int suggestedPrice,
          OffersResponse offers,
          List<String> categories
  ) {
    this.id = id;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
    this.categories = categories;
  }
}
