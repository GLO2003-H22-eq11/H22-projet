package ulaval.glo2003.product.domain.product;

import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.product.domain.product.productCategories.Categories;
import ulaval.glo2003.product.domain.product.productCategories.ProductCategory;
import ulaval.glo2003.product.domain.product.productId.ProductId;
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
          List<ProductCategory> productCategories,
          LocalDateTime createdAt
  ) {
    this.sellerId = sellerId;
    this.productId = productId;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
    this.categories = new Categories(productCategories);
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

  public int getSuggestedPriceAmount() {
    return this.suggestedPrice.getAmount();
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

  public List<ProductCategory> getProductCategories() {
    return this.categories.getProductCategories();
  }

  public Categories getCategories() {
    return categories;
  }

  public boolean hasNotTheSameTitle(String title) {
    return !(this.title.equals(title));
  }

  public boolean hasNotTheSameSellerId(SellerId sellerId) {
    return !(this.sellerId.equals(sellerId));
  }

  public boolean hasNotTheSameCategories(Categories categories) {
    if (this.categories.numberOfProductCategories() != categories.numberOfProductCategories()) {
      return true;
    }

    for (int i = 0; i < this.categories.numberOfProductCategories(); i++) {
      if (!(this.categories.getProductCategories().get(0).getCategoryName()
              .equals(categories.getProductCategories().get(0).getCategoryName()))) {
        return true;
      }
    }

    return false;
  }
}
