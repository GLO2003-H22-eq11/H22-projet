package ulaval.glo2003.product.api.product;

import ulaval.glo2003.product.domain.product.ProductCategory;

public class ProductCategoryAssembler {

  public ProductCategory toDomain(String category) {
    return new ProductCategory(category);
  }
}
