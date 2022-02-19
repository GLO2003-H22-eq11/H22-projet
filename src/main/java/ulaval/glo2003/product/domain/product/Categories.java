package ulaval.glo2003.product.domain.product;

import java.util.List;

public class Categories {

  private List<ProductCategory> productCategories;

  public Categories(List<ProductCategory> productCategories) {
    this.productCategories = productCategories;
  }

  public List<ProductCategory> getProductCategories() {
    return this.productCategories;
  }

  public boolean isEmpty() {
    return this.productCategories.isEmpty();
  }

  public boolean containsSimilarCategories(Categories categories) {
    for (ProductCategory category : categories.getProductCategories()) {
      if (productCategories.contains(category)) {
        return true;
      }
    }

    return false;
  }
}
