package ulaval.glo2003.seller.api.response;

import ulaval.glo2003.product.api.response.OffersSummaryResponse;

import java.util.List;

public class SellerProductResponse {
  public String id;
  public String createdAt;
  public String title;
  public String description;
  public Double suggestedPrice;
  public OffersSummaryResponse offers;
  public List<String> categories;

  public SellerProductResponse() {
  }

  public SellerProductResponse(
          String id,
          String createdAt,
          String title,
          String description,
          Double suggestedPrice,
          OffersSummaryResponse offers,
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
