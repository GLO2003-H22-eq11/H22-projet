package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductFiltererTest {

  private final String A_PRODUCT_TITLE = "MARIN_TITLE";
  private final String A_SIMILAR_PRODUCT_TITLE = "TITLE_MARIN";
  private final Amount THE_LOWEST_AMOUNT_POSSIBLE = Amount.fromInt(2);
  private final Amount AN_AMOUNT = Amount.fromInt(30);
  private final Amount A_MINIMAL_AMOUNT = Amount.fromInt(10);
  private final Amount A_MAXIMAL_AMOUNT = Amount.fromInt(20);
  private final SellerId A_SELLER_ID = new SellerId();
  private final SellerId ANOTHER_SELLER_ID = new SellerId();

  private final Product A_PRODUCT_WITH_A_TITLE = new ProductBuilder().withTitle(A_PRODUCT_TITLE).build();
  private final Product A_PRODUCT_WITH_A_SIMILAR_TITLE = new ProductBuilder().withTitle(A_SIMILAR_PRODUCT_TITLE).build();

  private final Product A_PRODUCT_WITH_A_SELLER_ID = new ProductBuilder().withSellerId(A_SELLER_ID).build();

  private List<Product> PRODUCTS;
  private ProductFilters A_PRODUCT_FILTERS;

  @Mock
  private ProductRepository productRepository;

  private ProductFilterer productFilterer;

  @BeforeEach
  public void setUp() {
    this.productFilterer = new ProductFilterer(this.productRepository);
    PRODUCTS = new ArrayList<>();
    A_PRODUCT_FILTERS = new ProductFilters();
  }

  @Test
  public void givenAProductWithATitleAndAProductFiltersWithTheSameTitleAsProduct_whenFindFilteredProducts_thenShouldFindProduct() {
    A_PRODUCT_FILTERS.setTitle(A_PRODUCT_TITLE);
    givenProducts(List.of(A_PRODUCT_WITH_A_TITLE));

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(A_PRODUCT_WITH_A_TITLE), actual);
  }

  @Test
  public void givenAProductWithATitleAndAProductFiltersWithSmallPartOfTheTitleAsProduct_whenFindFilteredProducts_thenShouldFindProduct() {
    String aSmallPartOfProductTitle = "MAR";
    A_PRODUCT_FILTERS.setTitle(aSmallPartOfProductTitle);
    givenProducts(List.of(A_PRODUCT_WITH_A_TITLE));

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(A_PRODUCT_WITH_A_TITLE), actual);
  }

  @Test
  public void givenAProductWithATitleAndAProductFiltersWithSmallPartOfTheTitleAsProductWithDifferentCasing_whenFindFilteredProducts_thenShouldFindProduct() {
    String aSmallPartOfProductTitle = "N_tiT";
    A_PRODUCT_FILTERS.setTitle(aSmallPartOfProductTitle);
    givenProducts(List.of(A_PRODUCT_WITH_A_TITLE));

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(A_PRODUCT_WITH_A_TITLE), actual);
  }

  @Test
  public void givenAProductWithATitleAndAProductFiltersWithATitleNotInProductTitle_whenFindFilteredProducts_thenShouldNotFindProduct() {
    String aDifferentTitle = "yeeeee";
    A_PRODUCT_FILTERS.setTitle(aDifferentTitle);
    PRODUCTS.add(A_PRODUCT_WITH_A_TITLE);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertTrue(actual.isEmpty());
  }

  @Test
  public void givenTwoProductsWithSimilarTitlesAndAProductFiltersWithATitleCorrespondingToBothProducts_whenFindFilteredProducts_thenShouldFindProducts() {
    String samePartOfTitle = "MARIN";
    A_PRODUCT_FILTERS.setTitle(samePartOfTitle);
    PRODUCTS.add(A_PRODUCT_WITH_A_TITLE);
    PRODUCTS.add(A_PRODUCT_WITH_A_SIMILAR_TITLE);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(A_PRODUCT_WITH_A_TITLE, A_PRODUCT_WITH_A_SIMILAR_TITLE), actual);
  }

  @Test
  public void givenAProductAndAProductFiltersWithSameProductId_whenFindFilteredProducts_thenShouldFindProduct() {
    A_PRODUCT_FILTERS.setSellerId(A_SELLER_ID);
    PRODUCTS.add(A_PRODUCT_WITH_A_SELLER_ID);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(A_PRODUCT_WITH_A_SELLER_ID), actual);
  }

  @Test
  public void givenAProductAndAProductFiltersWithDifferentProductId_whenFindFilteredProducts_thenShouldNotFindProduct() {
    A_PRODUCT_FILTERS.setSellerId(ANOTHER_SELLER_ID);
    PRODUCTS.add(A_PRODUCT_WITH_A_SELLER_ID);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertTrue(actual.isEmpty());
  }

  @Test
  public void givenAProductWithAPriceAndAMinimalPriceLowerThanProductPrice_whenFindFilteredProducts_thenShouldFindProduct() {
    //Product product = new ProductBuilder().withAmount(Amount.fromInt())
    A_PRODUCT_FILTERS.setMinimalPrice(A_MINIMAL_AMOUNT);

  }

  @Test
  public void givenAProductWithAPriceAndAMinimalPriceHigherThanProductPrice_whenFindFilteredProducts_thenShouldNotFindProduct() {


  }

  @Test
  public void givenAProductWithAPriceAndAMaximumPriceLowerThanProductPrice_whenFindFilteredProducts_thenShouldNotFindProduct() {

  }

  @Test
  public void givenAProductWithAPriceAndAMaximumPriceHigherThanProductPrice_whenFindFilteredProducts_thenShouldFindProduct() {


  }

  private void givenProducts(List<Product> products) {
    given(this.productRepository.findAll()).willReturn(products);
  }

}