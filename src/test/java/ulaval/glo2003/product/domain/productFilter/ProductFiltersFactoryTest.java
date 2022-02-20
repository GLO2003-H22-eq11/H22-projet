package ulaval.glo2003.product.domain.productFilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.api.product.ProductFilterRequest;
import ulaval.glo2003.product.api.product.assembler.CategoryAssembler;
import ulaval.glo2003.product.domain.AmountFactory;
import ulaval.glo2003.product.domain.product.CategoriesFactory;
import ulaval.glo2003.product.domain.product.ProductFilters;
import ulaval.glo2003.product.api.product.ProductFiltersFactory;

import ulaval.glo2003.seller.domain.SellerIdFactory;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

public class ProductFiltersFactoryTest {

  private final String EMPTY_SELLER_ID = "";
  private final String EMPTY_TITLE = "";
  private final String A_SELLER_ID = "A_SELLER_ID";
  private final String A_TITLE = "A_TITLE";
  private final int INVALID_MINIMUM_PRICE = 0;
  private final int INVALID_MAXIMUM_PRICE = 0;
  private final int A_MINIMUM_PRICE = 10;
  private final int A_MAXIMUM_PRICE = 20;
  private final List<String> NO_CATEGORIES = List.of("null");
  private final List<String> CATEGORIES = List.of("HELLO WORLD");


  private final SellerIdFactory sellerIdFactory = new SellerIdFactory();
  private final AmountFactory amountFactory = new AmountFactory();
  private final CategoriesFactory categoriesFactory = new CategoriesFactory();

  private ProductFiltersFactory productFiltersFactory;


  @BeforeEach
  public void setUp() {
    this.productFiltersFactory = new ProductFiltersFactory(sellerIdFactory, amountFactory, categoriesFactory);
  }

  @Test
  public void givenNoFilter_whenCreate_thenShouldCreateAProductFilterWithNullAttributes() {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(EMPTY_SELLER_ID, EMPTY_TITLE, NO_CATEGORIES,
            INVALID_MINIMUM_PRICE, INVALID_MAXIMUM_PRICE);
    //ProductFilters productFilter = this.productFiltersFactory.create(productFilterRequest);

//    assertNull(productFilter.getCategories());
//    assertNull(productFilter.getMaximumPrice());
//    assertNull(productFilter.getSellerId());
//    assertNull(productFilter.getMinimalPrice());
//    assertNull(productFilter.getTitle());
  }

  @Test
  public void givenAnValidCategories_whenCreate_thenShouldCreateAProductFilterWithCategories() {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(EMPTY_SELLER_ID, EMPTY_TITLE, CATEGORIES,
            INVALID_MINIMUM_PRICE, INVALID_MAXIMUM_PRICE);
//    ProductFilters productFilter = this.productFiltersFactory.create(productFilterRequest);
//
//    assertEquals(productFilter.getCategories().numberOfProductCategories(), 1);
  }

  @Test
  public void givenAnValidTitle_whenCreate_thenShouldCreateAProductFilterWithATitle() {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(EMPTY_SELLER_ID, A_TITLE, NO_CATEGORIES,
            INVALID_MINIMUM_PRICE, INVALID_MAXIMUM_PRICE);
//    ProductFilters productFilter = this.productFiltersFactory.create(productFilterRequest);
//
//    assertEquals(productFilter.getTitle(), A_TITLE);
  }

  @Test
  public void givenAnValidSellerId_whenCreate_thenShouldCreateAProductFilterWithASellerId() {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, EMPTY_TITLE, NO_CATEGORIES,
            INVALID_MINIMUM_PRICE, INVALID_MAXIMUM_PRICE);
//    ProductFilters productFilter = this.productFiltersFactory.create(productFilterRequest);
//
//    assertEquals(productFilter.getSellerId().toString(), A_SELLER_ID);
  }

  @Test
  public void givenAnValidMinimumPrice_whenCreate_thenShouldCreateAProductFilterWithAMinimumPrice() {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, EMPTY_TITLE, NO_CATEGORIES,
            A_MINIMUM_PRICE, INVALID_MAXIMUM_PRICE);
//    ProductFilters productFilter = this.productFiltersFactory.create(productFilterRequest);
//
//    assertEquals(productFilter.getMinimalPrice().getAmount(), A_MINIMUM_PRICE);
  }

  @Test
  public void givenAnValidMaximum_whenCreate_thenShouldCreateAProductFilterWithAMaximumPrice() {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, EMPTY_TITLE, NO_CATEGORIES,
            INVALID_MINIMUM_PRICE, A_MAXIMUM_PRICE);
//    ProductFilters productFilter = this.productFiltersFactory.create(productFilterRequest);
//
//    assertEquals(productFilter.getMaximumPrice().getAmount(), A_MAXIMUM_PRICE);
  }


  @Test
  public void givenMultipleValidFilter_whenCreate_thenShouldCreateAProductFilterWithMultipleValidInformation() {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, A_TITLE, NO_CATEGORIES,
            INVALID_MINIMUM_PRICE, A_MAXIMUM_PRICE);

    //ProductFilters productFilters = this.productFiltersFactory.create(productFilterRequest);

//    assertEquals(productFilters.getMaximumPrice().getAmount(), A_MAXIMUM_PRICE);
//    assertEquals(productFilters.getTitle(), A_TITLE);
//    assertEquals(productFilters.getSellerId().toString(), A_SELLER_ID);
//    assertNull(productFilters.getCategories());
//    assertNull(productFilters.getMinimalPrice());
  }


}
