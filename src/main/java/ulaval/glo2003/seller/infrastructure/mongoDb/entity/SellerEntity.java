package ulaval.glo2003.seller.infrastructure.mongoDb.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity("Sellers")
public class SellerEntity {

  @Id
  public String sellerId;
  private String name;
  private String bio;
  private LocalDate birthDate;
  private LocalDateTime createdAt;


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
    return createdAt;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public String getName() {
    return name;
  }

  public String getBio() {
    return bio;
  }

  public String getSellerId() {
    return sellerId;
  }
}
