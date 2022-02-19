package ulaval.glo2003.product.api.product;

import java.util.List;

public class ProductFilterRequest {
  public String sellerId;
  public String title;
  public List<String> categories;
  public int minPrice;
  public int maxPrice;

  public ProductFilterRequest(String sellerId, String title, List<String> categories, int minPrice, int maxPrice) {
    this.sellerId = sellerId;
    this.title = title;
    this.categories = categories;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
  }
}
