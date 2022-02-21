package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoriesTest {

  private final Category A_CATEGORY = new Category("a category name");
  private final Category ANOTHER_CATEGORY = new Category("another category name");
  private final Categories CATEGORIES = new Categories(List.of(A_CATEGORY, ANOTHER_CATEGORY));

  @Test
  public void givenCategoriesAndAnotherSameCategories_whenHasAtLeastOneCategoryInCommon_thenShouldReturnTrue() {
    assertTrue(CATEGORIES.hasAtLeastOneCategoryInCommon(CATEGORIES));
  }

  @Test
  public void givenCategoriesAndAnotherCategoriesWithOneCommonCategory_whenHasAtLeastOneCategoryInCommon_thenShouldReturnTrue() {
    Categories categoriesWithCommonCategory = new Categories(List.of(A_CATEGORY));

    assertTrue(categoriesWithCommonCategory.hasAtLeastOneCategoryInCommon(CATEGORIES));
  }

  @Test
  public void givenCategoriesAndAnotherCategoriesWithNoCommonCategory_whenHasAtLeastOneCategoryInCommon_thenShouldReturnFalse() {
    Category aNewCategory = new Category("NEW CATEG");
    Categories categoriesWithNoCommonCategory = new Categories(List.of(aNewCategory));

    assertFalse(categoriesWithNoCommonCategory.hasAtLeastOneCategoryInCommon(CATEGORIES));
  }
}