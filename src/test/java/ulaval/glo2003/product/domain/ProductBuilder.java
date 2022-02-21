package ulaval.glo2003.product.domain;

import ulaval.glo2003.seller.domain.SellerId;

import java.time.LocalDateTime;
import java.util.List;

public class ProductBuilder {
  private SellerId sellerId;
  private ProductId productId;
  private String title;
  private final String description;
  private Amount suggestedPrice;
  private final Offers offers;
  private Categories categories;
  private final LocalDateTime createdAt;

  public ProductBuilder() {
    this.sellerId = new SellerId();
    this.productId = new ProductId();
    this.title = "MarinoBoy";
    this.description = "Je suis le meilleur programmeur à l'uni";
    this.suggestedPrice = Amount.fromInt(25);
    this.offers = null;
    this.categories = new Categories(List.of(new Category("category")));
    this.createdAt = LocalDateTime.now();
  }

  public ProductBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public ProductBuilder withAmount(Amount amount) {
    this.suggestedPrice = amount;
    return this;
  }

  public ProductBuilder withSellerId(SellerId sellerId) {
    this.sellerId = sellerId;
    return this;
  }

  public ProductBuilder withProductId(ProductId productId) {
    this.productId = productId;
    return this;
  }

  public ProductBuilder withCategories(Categories categories) {
    this.categories = categories;
    return this;
  }

  public Product build() {
    return new Product(
            this.sellerId,
            this.productId,
            this.title,
            this.description,
            this.suggestedPrice,
            this.offers,
            this.categories,
            this.createdAt
    );
  }
}
