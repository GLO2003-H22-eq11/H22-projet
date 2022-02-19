package ulaval.glo2003.product.api.product;

import ulaval.glo2003.product.domain.product.ProductFilter;

import java.util.List;

public class ProductFilterFactory {

  public ProductFilter create(String sellerId, String title, List<String> categories, int minPrice, int maxPrice) {
    ProductFilter productFilter = new ProductFilter();

    if (sellerId != null) {

    }

    if (title != null && !title.isBlank()) {
      productFilter.setTitle(title);
    }

    if (!categories.isEmpty()) {

    }

    if (minPrice > 0) {

    }

    if (maxPrice > 0) {

    }

    return productFilter;
  }
}
