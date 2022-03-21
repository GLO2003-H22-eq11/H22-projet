package ulaval.glo2003.product.domain;

import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.product.domain.exceptions.InvalidOfferPriceException;
import ulaval.glo2003.seller.domain.SellerId;

import java.time.LocalDateTime;
import java.util.List;

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

  public void addOffer(Offer offer) throws InvalidOfferPriceException {
    if (offer.getAmount().isHigherOrEqual(this.suggestedPrice)) {
      this.offers.addOffer(offer);
    } else {
      throw new InvalidOfferPriceException();
    }
  }
}
