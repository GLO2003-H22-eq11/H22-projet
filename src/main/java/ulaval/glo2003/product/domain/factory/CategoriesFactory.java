package ulaval.glo2003.product.domain.factory;

import ulaval.glo2003.product.domain.Categories;
import ulaval.glo2003.product.domain.Category;

import java.util.LinkedList;
import java.util.List;

public class CategoriesFactory {

  public Categories create(List<String> stringCategories) {
    List<Category> productsCategories = new LinkedList<>();
    for (String productCategory: stringCategories) {
      productsCategories.add(new Category(productCategory));
    }
    return new Categories(productsCategories);
  }
}
