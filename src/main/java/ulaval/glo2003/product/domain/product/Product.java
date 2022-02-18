package ulaval.glo2003.product.domain.product;

import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.seller.domain.SellerId;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Product {
  private final SellerId sellerId;
  private final ProductId productId;
  private final String title;
  private final String description;
  private final Amount suggestedPrice;
  private final Offers offers;
  private final List<ProductCategory> productCategories;
  private final LocalDateTime createdAt;

  public Product(
          SellerId sellerId,
          ProductId productId,
          String title,
          String description,
          Amount suggestedPrice,
          Offers offers,
          List<ProductCategory> productCategories,
          LocalDateTime createdAt
  ) {
    this.sellerId = sellerId;
    this.productId = productId;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
    this.productCategories = productCategories;
  }

  public SellerId getSellerId() {
    return this.sellerId;
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

  public String getStringId() {
    return this.productId.toString();
  }

  public ProductId getProductId() {
    return this.productId;
  }

  public List<ProductCategory> getCategories() {
    return this.productCategories;
  }
}
