package ulaval.glo2003.product.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.Categories;
import ulaval.glo2003.product.domain.CategoriesFactory;
import ulaval.glo2003.product.domain.Category;
import ulaval.glo2003.product.domain.ProductFilters;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerIdFactory;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductFiltersFactoryTest {

  private final static String EMPTY_SELLER_ID = "";
  private final static String EMPTY_TITLE = "";
  private final static String A_CATEGORY_NAME = "CATEGO";
  private final static String A_SELLER_ID = "A_SELLER_ID";
  private final static String A_TITLE = "A_TITLE";

  private final int INVALID_MINIMUM_PRICE = 0;
  private final int INVALID_MAXIMUM_PRICE = 0;
  private final int A_MINIMUM_PRICE = 10;
  private final int A_MAXIMUM_PRICE = 20;
  private final List<String> NO_CATEGORIES = Collections.emptyList();
  private final List<String> CATEGORIES = List.of(A_CATEGORY_NAME);


  private final SellerIdFactory sellerIdFactory = new SellerIdFactory();
  private final CategoriesFactory categoriesFactory = new CategoriesFactory();

  private ProductFiltersFactory productFiltersFactory;


  @BeforeEach
  public void setUp() {
    this.productFiltersFactory = new ProductFiltersFactory(sellerIdFactory, categoriesFactory);
  }

  @Test
  public void givenNoFilter_whenCreate_thenShouldCreateAProductFilterWithNullAttributes() {
    ProductFilters productFilters = this.productFiltersFactory.create(EMPTY_SELLER_ID, EMPTY_TITLE, NO_CATEGORIES, INVALID_MINIMUM_PRICE, INVALID_MAXIMUM_PRICE);

    assertNull(productFilters.getCategories());
    assertNull(productFilters.getMaximumPrice());
    assertNull(productFilters.getSellerId());
    assertNull(productFilters.getMinimalPrice());
    assertNull(productFilters.getTitle());
  }

  @Test
  public void givenAValidCategories_whenCreate_thenShouldCreateAProductFilterWithCategories() {
    ProductFilters productFilters = this.productFiltersFactory.create(EMPTY_SELLER_ID, EMPTY_TITLE, CATEGORIES, INVALID_MINIMUM_PRICE, INVALID_MAXIMUM_PRICE);

    Categories actual = new Categories(List.of(new Category(A_CATEGORY_NAME)));

    assertEquals(productFilters.getCategories(), actual);
  }

  @Test
  public void givenAValidTitle_whenCreate_thenShouldCreateAProductFilterWithATitle() {
    ProductFilters productFilters = this.productFiltersFactory.create(EMPTY_SELLER_ID, A_TITLE, NO_CATEGORIES, INVALID_MINIMUM_PRICE, INVALID_MAXIMUM_PRICE);

    assertEquals(productFilters.getTitle(), A_TITLE);
  }

  @Test
  public void givenAValidSellerId_whenCreate_thenShouldCreateAProductFilterWithASellerId() {
    ProductFilters productFilters = this.productFiltersFactory.create(A_SELLER_ID, EMPTY_TITLE, NO_CATEGORIES, INVALID_MINIMUM_PRICE, INVALID_MAXIMUM_PRICE);

    assertEquals(productFilters.getSellerId(), new SellerId(A_SELLER_ID));
  }

  @Test
  public void givenAValidMinimumPrice_whenCreate_thenShouldCreateAProductFilterWithAMinimumPrice() {
    ProductFilters productFilters = this.productFiltersFactory.create(EMPTY_SELLER_ID, EMPTY_TITLE, NO_CATEGORIES, A_MINIMUM_PRICE, INVALID_MAXIMUM_PRICE);

    assertEquals(productFilters.getMinimalPrice(), Amount.fromInt(A_MINIMUM_PRICE));
  }

  @Test
  public void givenAnValidMaximum_whenCreate_thenShouldCreateAProductFilterWithAMaximumPrice() {
    ProductFilters productFilters = this.productFiltersFactory.create(EMPTY_SELLER_ID, EMPTY_TITLE, NO_CATEGORIES, INVALID_MINIMUM_PRICE, A_MAXIMUM_PRICE);

    assertEquals(productFilters.getMaximumPrice(), Amount.fromInt(A_MAXIMUM_PRICE));
  }


  @Test
  public void givenMultipleValidFilters_whenCreate_thenShouldCreateAValidProductFilter() {
    ProductFilters productFilters = this.productFiltersFactory.create(A_SELLER_ID, EMPTY_TITLE, CATEGORIES, INVALID_MINIMUM_PRICE, A_MAXIMUM_PRICE);

    Categories actual = new Categories(List.of(new Category(A_CATEGORY_NAME)));

    assertEquals(productFilters.getMaximumPrice(), Amount.fromInt(A_MAXIMUM_PRICE));
    assertEquals(productFilters.getCategories(), actual);
    assertEquals(productFilters.getSellerId(), new SellerId(A_SELLER_ID));
    assertNull(productFilters.getTitle());
    assertNull(productFilters.getMinimalPrice());
  }
}
