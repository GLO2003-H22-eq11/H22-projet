package ulaval.glo2003.product.api;

public class ProductResponse {
  public final String productId;
  public final String createdAt;
  public final String title;
  public final String description;
  public final int suggestedPrice;
  public final OffersResponse offers;

  public ProductResponse(
          String productId,
          String createdAt,
          String title,
          String description,
          int suggestedPrice,
          OffersResponse offers
  ) {
    this.productId = productId;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
  }
}
