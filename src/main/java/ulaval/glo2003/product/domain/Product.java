package ulaval.glo2003.product.domain;

import ulaval.glo2003.product.domain.exceptions.InvalidOfferPriceException;
import ulaval.glo2003.seller.domain.SellerId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Product {
  private final SellerId sellerId;
  private final ProductId productId;
  private final String title;
  private final String description;
  private final Amount suggestedPrice;
  private final Offers offers;
  private final Categories categories;
  private final LocalDateTime createdAt;

  public Product(
          SellerId sellerId,
          ProductId productId,
          String title,
          String description,
          Amount suggestedPrice,
          Offers offers,
          Categories categories,
          LocalDateTime createdAt
  ) {
    this.sellerId = sellerId;
    this.productId = productId;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
    this.categories = categories;
  }

  public SellerId getSellerId() {
    return this.sellerId;
  }

  public String getStringProductId() {
    return this.productId.toString();
  }

  public String getStringSellerId() {
    return this.sellerId.toString();
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

  public Amount getSuggestedPriceAmount() {
    return this.suggestedPrice;
  }

  public Double getSuggestedPriceAmountDoubleValue() {
    return this.suggestedPrice.getDoubleValue();
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

  public List<Category> getProductCategories() {
    return this.categories.getCategories();
  }

  public Categories getCategories() {
    return categories;
  }

  public boolean isInTitle(String value) {
    return this.title.toLowerCase().contains(value.toLowerCase());
  }

  public boolean hasSameSellerId(SellerId sellerId) {
    return this.sellerId.equals(sellerId);
  }

  public boolean hasAtLeastOneCategoryInCommon(Categories categories) {
    return this.categories.hasAtLeastOneCategoryInCommon(categories);
  }

  public void addOfferAmount(Amount offerAmount) throws InvalidOfferPriceException {
    if (offerAmount.isHigherOrEqual(this.suggestedPrice)) {
      this.offers.addOfferAmount(offerAmount);
    } else {
      throw new InvalidOfferPriceException();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return sellerId.equals(product.sellerId)
            && productId.equals(product.productId)
            && title.equals(product.title)
            && description.equals(product.description) && suggestedPrice.equals(product.suggestedPrice)
            && offers.equals(product.offers) && categories.equals(product.categories)
            && createdAt.equals(product.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sellerId, productId, title, description, suggestedPrice, offers, categories, createdAt);
  }
}
