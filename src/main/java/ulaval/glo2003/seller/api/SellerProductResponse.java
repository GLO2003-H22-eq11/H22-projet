package ulaval.glo2003.seller.api;

import ulaval.glo2003.product.api.response.OffersResponse;

import java.util.List;

public class SellerProductResponse {
  public final String id;
  public final String createdAt;
  public final String title;
  public final String description;
  public final int suggestedPrice;
  public final OffersResponse offers;
  public final List<String> categories;

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
