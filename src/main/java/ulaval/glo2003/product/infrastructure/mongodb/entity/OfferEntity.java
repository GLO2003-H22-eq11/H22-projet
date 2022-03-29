package ulaval.glo2003.product.infrastructure.mongodb.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("Offers")
public class OfferEntity {
  @Id
  private String offerId;
  private String productId;
  private String name;
  private String email;
  private String phoneNumber;
  private Double amount;
  private String message;
  private String createdAt;

  public OfferEntity() {
  }

  public OfferEntity(
          String offerId,
          String productId,
          String name,
          String email,
          String phoneNumber,
          Double amount,
          String message,
          String createdAt
  ) {
    this.offerId = offerId;
    this.productId = productId;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.amount = amount;
    this.message = message;
    this.createdAt = createdAt;
  }

  public String getOfferId() {
    return this.offerId;
  }

  public String getProductId() {
    return this.productId;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public Double getAmount() {
    return this.amount;
  }

  public String getMessage() {
    return this.message;
  }

  public String getCreatedAt() {
    return this.createdAt;
  }
}
