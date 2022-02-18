package ulaval.glo2003.seller.domain;

import ulaval.glo2003.product.domain.product.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SellerBuilder {
  private SellerId sellerId;
  private String name;
  private String bio;
  private LocalDate birthDate;
  private final LocalDateTime createdAt;
  private List<Product> products;

  public SellerBuilder() {
    this.sellerId = new SellerId();
    this.name = "MarinoBoi";
    this.bio = "Je suis le meilleur programmeur à l'uni";
    this.birthDate = LocalDate.of(2000, 10, 5);
    this.createdAt = LocalDateTime.now();
    this.products = new ArrayList<>();
  }

  public SellerBuilder withSellerId(SellerId sellerId) {
    this.sellerId = sellerId;
    return this;
  }

  public SellerBuilder withBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public Seller build() {
    return new Seller(
            this.sellerId,
            this.name,
            this.bio,
            this.birthDate,
            this.createdAt,
            this.products
    );
  }
}
