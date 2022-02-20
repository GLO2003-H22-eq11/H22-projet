package ulaval.glo2003.product.domain.product;

import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.seller.domain.SellerId;

public class ProductFilters {
  private final SellerId sellerId;
  private final String title;
  private final Categories categories;
  private final Amount minimalPrice;
  private final Amount maximumPrice;

  public ProductFilters(
          SellerId sellerId, String title, Categories categories, Amount minimalPrice, Amount maximumPrice
  ) {
    this.sellerId = sellerId;
    this.title = title;
    this.categories = categories;
    this.minimalPrice = minimalPrice;
    this.maximumPrice = maximumPrice;
  }

  public Amount getMaximumPrice() {
    return this.maximumPrice;
  }

  public Amount getMinimalPrice() {
    return this.minimalPrice;
  }

  public Categories getCategories() {
    return this.categories;
  }

  public SellerId getSellerId() {
    return this.sellerId;
  }

  public String getTitle() {
    return this.title;
  }
}
