package ulaval.glo2003.product.api;

import ulaval.glo2003.product.domain.ProductCategory;

public class ProductCategoryAssembler {

  public ProductCategory toDomain(String category) {
    return new ProductCategory(category);
  }
}
