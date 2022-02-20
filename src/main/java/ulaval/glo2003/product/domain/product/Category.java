package ulaval.glo2003.product.domain.product;

import java.util.Objects;

public class Category {
  private final String categoryName;

  public Category(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryName() {
    return this.categoryName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Category category = (Category) o;
    return categoryName.equals(category.categoryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoryName);
  }
}
