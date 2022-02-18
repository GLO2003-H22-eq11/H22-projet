package ulaval.glo2003.product.domain;

import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.ProductCategory;
import ulaval.glo2003.product.domain.product.ProductId;
import ulaval.glo2003.seller.domain.SellerId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProductBuilder {
  private SellerId sellerId;
  private ProductId productId;
  private final String title;
  private final String description;
  private final Amount suggestedPrice;
  private final Offers offers;
  private final List<ProductCategory> productCategories;
  private final LocalDateTime createdAt;

  public ProductBuilder() {
    this.sellerId = new SellerId();
    this.productId = new ProductId();
    this.title = "MarinoBoy";
    this.description = "Je suis le meilleur programmeur à l'uni";
    this.suggestedPrice = new Amount(BigDecimal.valueOf(25));
    this.offers = null;
    this.productCategories = List.of(new ProductCategory("category"));
    this.createdAt = LocalDateTime.now();
  }

  public ProductBuilder withSellerId(SellerId sellerId) {
    this.sellerId = sellerId;
    return this;
  }

  public ProductBuilder withProductId(ProductId productId) {
    this.productId = productId;
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
            this.productCategories,
            this.createdAt
    );
  }
}
