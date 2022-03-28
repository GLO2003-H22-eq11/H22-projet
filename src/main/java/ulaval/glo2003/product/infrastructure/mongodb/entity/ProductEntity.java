package ulaval.glo2003.product.infrastructure.mongodb.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.util.List;

@Entity("Products")
public class ProductEntity {

  @Id
  private String productId;
  private String sellerId;
  private String title;
  private String description;
  private Double suggestedPrice;
  private OffersEntity offers;
  private List<String> categories;
  private String createdAt;

  public ProductEntity() {
  }

  public ProductEntity(
          String productId,
          String sellerId,
          String title,
          String description,
          Double suggestedPrice,
          OffersEntity offers,
          List<String> categories,
          String createdAt
  ) {
    this.productId = productId;
    this.sellerId = sellerId;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
    this.categories = categories;
    this.createdAt = createdAt;
  }

  public String getSellerId() {
    return this.sellerId;
  }

  public String getProductId() {
    return this.productId;
  }

  public String getTitle() {
    return this.title;
  }

  public String getDescription() {
    return this.description;
  }

  public Double getSuggestedPrice() {
    return this.suggestedPrice;
  }

  public List<String> getCategories() {
    return categories;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public OffersEntity getOffers() {
    return this.offers;
  }
}
