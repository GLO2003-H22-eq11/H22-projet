package ulaval.glo2003.product.api.product.assembler;

import ulaval.glo2003.product.domain.product.Category;

public class CategoryAssembler {

  public Category toDomain(String category) {
    return new Category(category);
  }
}
