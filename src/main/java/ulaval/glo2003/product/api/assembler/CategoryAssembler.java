package ulaval.glo2003.product.api.assembler;

import ulaval.glo2003.product.domain.Category;

public class CategoryAssembler {

  public Category toDomain(String category) {
    return new Category(category);
  }
}
