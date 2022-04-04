package ulaval.glo2003.product.domain;

import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.seller.domain.SellerId;

public class ProductFilters {
  private SellerId sellerId;
  private String title;
  private Categories categories;
  private Amount minimalPrice;
  private Amount maximumPrice;

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

  public void setSellerId(SellerId sellerId) {
    this.sellerId = sellerId;
  }

  public void setCategories(Categories categories) {
    this.categories = categories;
  }

  public void setMaximumPrice(Amount maximumPrice) {
    this.maximumPrice = maximumPrice;
  }

  public void setMinimalPrice(Amount minimalPrice) {
    this.minimalPrice = minimalPrice;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
