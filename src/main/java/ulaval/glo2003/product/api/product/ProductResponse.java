package ulaval.glo2003.product.api.product;

import ulaval.glo2003.product.api.offers.OffersResponse;

import java.util.List;

public class ProductResponse {
  public final String id;
  public final String createdAt;
  public final String title;
  public final String description;
  public final int suggestedPrice;
  public final OffersResponse offers;
  public final List<String> categories;
  public final ProductSellerResponse seller;


  public ProductResponse(
          String id,
          String createdAt,
          String title,
          String description,
          int suggestedPrice,
          OffersResponse offers,
          List<String> categories,
          ProductSellerResponse productSellerResponse) {
    this.id = id;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
    this.categories = categories;
    this.seller = productSellerResponse;
  }
}
