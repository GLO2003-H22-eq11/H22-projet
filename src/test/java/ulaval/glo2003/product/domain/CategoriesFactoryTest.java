package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.Test;

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