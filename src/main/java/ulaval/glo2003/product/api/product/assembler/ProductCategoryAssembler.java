package ulaval.glo2003.product.api.product.assembler;

import ulaval.glo2003.product.domain.product.productCategories.ProductCategory;

public class ProductCategoryAssembler {

  public ProductCategory toDomain(String category) {
    return new ProductCategory(category);
  }
}
