package ulaval.glo2003.product.domain.factory;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Categories;
import ulaval.glo2003.product.domain.Category;
import ulaval.glo2003.product.domain.factory.CategoriesFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriesFactoryTest {

  private final CategoriesFactory categoriesFactory = new CategoriesFactory();

  @Test
  public void givenAListOfStringCategories_whenCreate_thenShouldCreateCategories() {
    String aCategoryName = "Test";
    String aSecondCategoryName = "Testing";
    List<String> categories = List.of(aCategoryName, aSecondCategoryName);
    Categories expected = new Categories(List.of(new Category(aCategoryName), new Category(aSecondCategoryName)));

    Categories actual = this.categoriesFactory.create(categories);

    assertEquals(expected, actual);
  }
}