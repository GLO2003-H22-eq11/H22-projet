package ulaval.glo2003.seller.infrastructure.mongoDb.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity("Sellers")
public class SellerEntity {

  @Id
  private String sellerId;
  private String name;
  private String bio;
  private LocalDate birthDate;
  private LocalDateTime createdAt;

  public SellerEntity() {
  }

  public SellerEntity(
          String sellerId,
          String name,
          String bio,
          LocalDate birthDate,
          LocalDateTime createdAt
  ) {
    this.sellerId = sellerId;
    this.name = name;
    this.bio = bio;
    this.birthDate = birthDate;
    this.createdAt = createdAt;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public LocalDate getBirthDate() {
    return this.birthDate;
  }

  public String getName() {
    return this.name;
  }

  public String getBio() {
    return this.bio;
  }

  public String getSellerId() {
    return this.sellerId;
  }
}
