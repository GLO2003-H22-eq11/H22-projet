package ulaval.glo2003.seller.api;

import ulaval.glo2003.product.api.OffersResponse;


public class SellerProductResponse {
  public final String id;
  public final String createdAt;
  public final String title;
  public final String description;
  public final int suggestedPrice;
  public final OffersResponse offers;

  public SellerProductResponse(String id, String createdAt, String title, String description, int suggestedPrice,
                               OffersResponse offers) {
    this.id = id;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
  }
}
