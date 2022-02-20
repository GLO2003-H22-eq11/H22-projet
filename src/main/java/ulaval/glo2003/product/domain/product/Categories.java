package ulaval.glo2003.product.domain.product;

import java.util.List;
import java.util.Objects;

public class Categories {

  private final List<Category> categories;

  public Categories(List<Category> categories) {
    this.categories = categories;
  }

  public List<Category> getCategories() {
    return this.categories;
  }

  public boolean hasAtLeastOneCategoryInCommon(Categories categories) {
    for (Category category : this.categories) {
      if (categories.contains(category)) {
        return true;
      }
    }
    return false;
  }

  private boolean contains(Category category) {
    return this.categories.contains(category);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Categories categories = (Categories) o;
    return this.categories.equals(categories.categories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categories);
  }

}
