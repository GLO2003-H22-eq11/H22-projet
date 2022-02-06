package ulaval.glo2003.products.api;

import ulaval.glo2003.products.domain.ProductCategory;

public class ProductCategoryAssembler {

  public ProductCategory toDomain(String category) {
    return new ProductCategory(category);
  }
}
