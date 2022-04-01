package ulaval.glo2003.product.domain;

import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.offer.domain.OffersSummary;
import ulaval.glo2003.seller.domain.SellerId;

import java.time.Instant;
import java.util.List;

public class ProductBuilder {
  private SellerId sellerId;
  private ProductId productId;
  private String title;
  private final String description;
  private Amount suggestedPrice;
  private OffersSummary offersSummary;
  private Categories categories;
  private final Instant createdAt;

  public ProductBuilder() {
    this.sellerId = new SellerId();
    this.productId = new ProductId();
    this.title = "MarinoBoy";
    this.description = "Max est le meilleur programmeur à l'uni";
    this.suggestedPrice = Amount.fromDouble(25.0);
    this.offersSummary = new OffersSummary();
    this.categories = new Categories(List.of(new Category("category")));
    this.createdAt = Instant.now();
  }

  public ProductBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public ProductBuilder withAmount(Amount amount) {
    this.suggestedPrice = amount;
    return this;
  }

  public ProductBuilder withOffers(OffersSummary offersSummary) {
    this.offersSummary = offersSummary;
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
            this.offersSummary,
            this.categories,
            this.createdAt
    );
  }
}
