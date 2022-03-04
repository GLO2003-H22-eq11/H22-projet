package ulaval.glo2003.product.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ulaval.glo2003.exception.GenericException;
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

  private final static String NULL_SELLER_ID = null;
  private final static String NULL_TITLE = null;
  private final static String A_CATEGORY_NAME = "CATEGO";
  private final static String A_SELLER_ID = "bd92e112-a7c1-4ae0-9ea2-0bf0734d0dfe";
  private final static String A_TITLE = "A_TITLE";

  private final Integer NULL_PRICE = null;
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
  public void givenNoFilter_whenCreate_thenShouldCreateAProductFilterWithNullAttributes() throws GenericException {
    ProductFilters productFilters = this.productFiltersFactory.create(NULL_SELLER_ID, NULL_TITLE, NO_CATEGORIES, NULL_PRICE, NULL_PRICE);

    assertNull(productFilters.getCategories());
    assertNull(productFilters.getMaximumPrice());
    assertNull(productFilters.getSellerId());
    assertNull(productFilters.getMinimalPrice());
    assertNull(productFilters.getTitle());
  }

  @Test
  public void givenAValidCategories_whenCreate_thenShouldCreateAProductFilterWithCategories() throws GenericException {
    ProductFilters productFilters = this.productFiltersFactory.create(NULL_SELLER_ID, NULL_TITLE, CATEGORIES, NULL_PRICE, NULL_PRICE);

    Categories actual = new Categories(List.of(new Category(A_CATEGORY_NAME)));

    assertEquals(productFilters.getCategories(), actual);
  }

  @Test
  public void givenAValidTitle_whenCreate_thenShouldCreateAProductFilterWithATitle() throws GenericException{
    ProductFilters productFilters = this.productFiltersFactory.create(NULL_SELLER_ID, A_TITLE, NO_CATEGORIES, NULL_PRICE, NULL_PRICE);

    assertEquals(productFilters.getTitle(), A_TITLE);
  }

  @Test
  public void givenAValidSellerId_whenCreate_thenShouldCreateAProductFilterWithASellerId() throws GenericException{
    ProductFilters productFilters = this.productFiltersFactory.create(A_SELLER_ID, NULL_TITLE, NO_CATEGORIES, NULL_PRICE, NULL_PRICE);

    assertEquals(productFilters.getSellerId(), new SellerId(A_SELLER_ID));
  }

  @Test
  public void givenAValidMinimumPrice_whenCreate_thenShouldCreateAProductFilterWithAMinimumPrice() throws GenericException{
    ProductFilters productFilters = this.productFiltersFactory.create(NULL_SELLER_ID, NULL_TITLE, NO_CATEGORIES, A_MINIMUM_PRICE, NULL_PRICE);

    assertEquals(productFilters.getMinimalPrice(), Amount.fromInt(A_MINIMUM_PRICE));
  }

  @Test
  public void givenAnValidMaximum_whenCreate_thenShouldCreateAProductFilterWithAMaximumPrice() throws GenericException{
    ProductFilters productFilters = this.productFiltersFactory.create(NULL_SELLER_ID, NULL_TITLE, NO_CATEGORIES, NULL_PRICE, A_MAXIMUM_PRICE);

    assertEquals(productFilters.getMaximumPrice(), Amount.fromInt(A_MAXIMUM_PRICE));
  }


  @Test
  public void givenMultipleValidFilters_whenCreate_thenShouldCreateAValidProductFilter() throws GenericException{
    ProductFilters productFilters = this.productFiltersFactory.create(A_SELLER_ID, NULL_TITLE, CATEGORIES, NULL_PRICE, A_MAXIMUM_PRICE);

    Categories actual = new Categories(List.of(new Category(A_CATEGORY_NAME)));

    assertEquals(productFilters.getMaximumPrice(), Amount.fromInt(A_MAXIMUM_PRICE));
    assertEquals(productFilters.getCategories(), actual);
    assertEquals(productFilters.getSellerId(), new SellerId(A_SELLER_ID));
    assertNull(productFilters.getTitle());
    assertNull(productFilters.getMinimalPrice());
  }
}
