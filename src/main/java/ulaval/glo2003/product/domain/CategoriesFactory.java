package ulaval.glo2003.product.domain;

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
