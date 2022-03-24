package ulaval.glo2003.seller.domain;

import ulaval.glo2003.product.domain.ProductWithOffers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SellerWithProductsOffers {

  private final SellerId sellerId;
  private final String name;
  private final String bio;
  private final LocalDate birthDate;
  private final LocalDateTime createdAt;
  private List<ProductWithOffers> products;

  public SellerWithProductsOffers(
          SellerId sellerId,
          String name,
          String bio,
          LocalDate birthDate,
          LocalDateTime createdAt,
          List<ProductWithOffers> products
  ) {
    this.sellerId = sellerId;
    this.name = name;
    this.bio = bio;
    this.birthDate = birthDate;
    this.createdAt = createdAt;
    this.products = products;
  }

  public SellerId getSellerId() {
    return this.sellerId;
  }

  public String getStringSellerId() {
    return this.sellerId.toString();
  }

  public String getName() {
    return this.name;
  }

  public String getStringCreatedAt() {
    return this.createdAt.toString();
  }

  public String getBio() {
    return this.bio;
  }

  public String getBirthDate() {
    return this.birthDate.toString();
  }

  public List<ProductWithOffers> getProducts() {
    return products;
  }
}
