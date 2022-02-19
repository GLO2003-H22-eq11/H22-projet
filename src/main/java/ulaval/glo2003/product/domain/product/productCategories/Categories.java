package ulaval.glo2003.product.domain.product.productCategories;

import java.util.List;
import java.util.Objects;

public class Categories {

  private List<ProductCategory> productCategories;

  public Categories(List<ProductCategory> productCategories) {
    this.productCategories = productCategories;
  }

  public List<ProductCategory> getProductCategories() {
    return this.productCategories;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Categories categories = (Categories) o;
    return productCategories.equals(categories.productCategories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productCategories);
  }


  public int numberOfProductCategories() {
    return this.productCategories.size();
  }

  public boolean hasNotTheSameProductsCategory(Categories categories) {
    if (this.numberOfProductCategories() != categories.numberOfProductCategories()) {
      return true;
    }

    for (int i = 0; i < this.numberOfProductCategories(); i++) {
      if (!(this.getProductCategories().get(0).getCategoryName()
              .equals(categories.getProductCategories().get(0).getCategoryName()))) {
        return true;
      }
    }

    return false;
  }
}
