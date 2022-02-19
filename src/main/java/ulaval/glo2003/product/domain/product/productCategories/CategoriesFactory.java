package ulaval.glo2003.product.domain.product.productCategories;

import ulaval.glo2003.product.api.product.ProductCategoryAssembler;

import java.util.LinkedList;
import java.util.List;

public class CategoriesFactory {

  private final ProductCategoryAssembler productCategoryAssembler;

  public CategoriesFactory(ProductCategoryAssembler productCategoryAssembler) {
    this.productCategoryAssembler = productCategoryAssembler;
  }

  public Categories create(List<String> stringProductCategories) {
    List<ProductCategory> productsCategories = new LinkedList<>();
    for (String productCategory: stringProductCategories) {
      productsCategories.add(this.productCategoryAssembler.toDomain(productCategory));
    }
    return new Categories(productsCategories);
  }
}
