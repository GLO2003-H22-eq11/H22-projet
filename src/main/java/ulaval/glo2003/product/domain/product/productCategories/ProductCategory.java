package ulaval.glo2003.product.domain.product.productCategories;

public class ProductCategory {
  private final String categoryName;

  public ProductCategory(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryName() {
    return this.categoryName;
  }
}
