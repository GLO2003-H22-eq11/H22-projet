package ulaval.glo2003.seller.domain;

import java.time.LocalDateTime;

public class Product {
  private final ProductId productId;
  private final LocalDateTime createdAt;
  private final String title;
  private final String description;
  private final Amount suggestedPrice;
  private final Offers offers;


  public Product(
          ProductId productId,
          LocalDateTime createdAt,
          String title,
          String description,
          Amount suggestedPrice,
          Offers offers
  ) {
    this.productId = productId;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
  }

  public String getStringProductId() {
    return this.productId.toString();
  }

  public String getStringCreatedAt() {
    return this.createdAt.toString();
  }

  public String getTitle() {
    return this.title;
  }

  public String getDescription() {
    return this.description;
  }

  public int getSuggestedPriceAmount() {
    return this.suggestedPrice.getAmount();
  }

  public Offers getOffers() {
    return this.offers;
  }
}
