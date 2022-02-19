package ulaval.glo2003.product.domain.product.productFilter;

import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.product.productCategories.Categories;
import ulaval.glo2003.seller.domain.SellerId;

public class ProductFilter {
  private SellerId sellerId;
  private String title;
  private Categories categories;
  private Amount minimalPrice;
  private Amount maximumPrice;

  public Amount getMaximumPrice() {
    return maximumPrice;
  }

  public Amount getMinimalPrice() {
    return minimalPrice;
  }

  public Categories getCategories() {
    return categories;
  }

  public SellerId getSellerId() {
    return sellerId;
  }

  public String getTitle() {
    return title;
  }

  public void setCategories(Categories categories) {
    this.categories = categories;
  }

  public void setMinimalPrice(Amount minimalPrice) {
    this.minimalPrice = minimalPrice;
  }

  public void setMaximumPrice(Amount maximumPrice) {
    this.maximumPrice = maximumPrice;
  }

  public void setSellerId(SellerId sellerId) {
    this.sellerId = sellerId;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
