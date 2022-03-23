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
  private final Amount AN_AMOUNT = Amount.fromDouble(30.0);
  private final Amount A_MINIMAL_AMOUNT = Amount.fromDouble(10.0);
  private final Amount A_MAXIMAL_AMOUNT = Amount.fromDouble(60.0);
  private final SellerId A_SELLER_ID = new SellerId();
  private final SellerId ANOTHER_SELLER_ID = new SellerId();

  private final Product A_PRODUCT_WITH_A_TITLE = new ProductBuilder().withTitle(A_PRODUCT_TITLE).build();
  private final Product A_PRODUCT_WITH_A_SIMILAR_TITLE = new ProductBuilder().withTitle(A_SIMILAR_PRODUCT_TITLE).build();

  private final Product A_PRODUCT_WITH_A_SELLER_ID = new ProductBuilder().withSellerId(A_SELLER_ID).build();

  private final Category A_CATEGORY = new Category("A category");
  private final Category ANOTHER_CATEGORY = new Category("This is another category");
  private final Categories PRODUCT_CATEGORIES = new Categories(List.of(A_CATEGORY, ANOTHER_CATEGORY));
  private final Product A_PRODUCT_WITH_TWO_CATEGORIES = new ProductBuilder().withCategories(PRODUCT_CATEGORIES).build();

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
    PRODUCTS.add(A_PRODUCT_WITH_A_TITLE);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(A_PRODUCT_WITH_A_TITLE), actual);
  }

  @Test
  public void givenAProductWithATitleAndAProductFiltersWithSmallPartOfTheTitleAsProduct_whenFindFilteredProducts_thenShouldFindProduct() {
    String aSmallPartOfProductTitle = "MAR";
    A_PRODUCT_FILTERS.setTitle(aSmallPartOfProductTitle);
    PRODUCTS.add(A_PRODUCT_WITH_A_TITLE);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(A_PRODUCT_WITH_A_TITLE), actual);
  }

  @Test
  public void givenAProductWithATitleAndAProductFiltersWithSmallPartOfTheTitleAsProductWithDifferentCasing_whenFindFilteredProducts_thenShouldFindProduct() {
    String aSmallPartOfProductTitle = "N_tiT";
    A_PRODUCT_FILTERS.setTitle(aSmallPartOfProductTitle);
    PRODUCTS.add(A_PRODUCT_WITH_A_TITLE);
    givenProducts(PRODUCTS);

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
  public void givenTwoProductAndAProductFiltersWithSameProductId_whenFindFilteredProducts_thenShouldFindProducts() {
    Product aProduct = new ProductBuilder().withSellerId(A_SELLER_ID).build();
    A_PRODUCT_FILTERS.setSellerId(A_SELLER_ID);
    PRODUCTS.add(A_PRODUCT_WITH_A_SELLER_ID);
    PRODUCTS.add(aProduct);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(A_PRODUCT_WITH_A_SELLER_ID, aProduct), actual);
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
    Product productWithHigherAmountThanMinimumAmount = new ProductBuilder().withAmount(AN_AMOUNT).build();
    A_PRODUCT_FILTERS.setMinimalPrice(A_MINIMAL_AMOUNT);
    PRODUCTS.add(productWithHigherAmountThanMinimumAmount);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(productWithHigherAmountThanMinimumAmount), actual);
  }

  @Test
  public void givenAProductWithAPriceAndAMinimalPriceHigherThanProductPrice_whenFindFilteredProducts_thenShouldNotFindProduct() {
    Double smallNumber = 2.0;
    Amount aVerySmallAmount = Amount.fromDouble(smallNumber);
    Product productWithLowerAmountThanMinimalAmount = new ProductBuilder().withAmount(aVerySmallAmount).build();
    A_PRODUCT_FILTERS.setMinimalPrice(A_MINIMAL_AMOUNT);
    PRODUCTS.add(productWithLowerAmountThanMinimalAmount);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertTrue(actual.isEmpty());
  }

  @Test
  public void givenAProductWithAPriceAndAMaximumPriceLowerThanProductPrice_whenFindFilteredProducts_thenShouldNotFindProduct() {
    Product productWithAHighPrice = new ProductBuilder().withAmount(A_MAXIMAL_AMOUNT).build();
    A_PRODUCT_FILTERS.setMaximumPrice(A_MINIMAL_AMOUNT);
    PRODUCTS.add(productWithAHighPrice);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertTrue(actual.isEmpty());
  }

  @Test
  public void givenAProductWithAPriceAndAMaximumPriceHigherThanProductPrice_whenFindFilteredProducts_thenShouldFindProduct() {
    Product productWithALowPrice = new ProductBuilder().withAmount(A_MINIMAL_AMOUNT).build();
    A_PRODUCT_FILTERS.setMaximumPrice(A_MAXIMAL_AMOUNT);
    PRODUCTS.add(productWithALowPrice);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(productWithALowPrice), actual);
  }

  @Test
  public void givenAProductWithAPriceAndAMinimumPriceWithSameValue_whenFindFilteredProducts_thenShouldFindProduct() {
    Product aProduct = new ProductBuilder().withAmount(AN_AMOUNT).build();
    A_PRODUCT_FILTERS.setMinimalPrice(AN_AMOUNT);
    PRODUCTS.add(aProduct);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(aProduct), actual);
  }

  @Test
  public void givenAProductWithAPriceAndAMaximumPriceWithSameValue_whenFindFilteredProducts_thenShouldFindProduct() {
    Product aProduct = new ProductBuilder().withAmount(AN_AMOUNT).build();
    A_PRODUCT_FILTERS.setMaximumPrice(AN_AMOUNT);
    PRODUCTS.add(aProduct);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(aProduct), actual);
  }

  @Test
  public void givenAProductWithTwoCategoriesAndOnlyOneOfThemCategoriesAsAFilter_whenFindFilteredProducts_thenShouldFindProduct() {
    Categories filterCategories = new Categories(List.of(A_CATEGORY));
    A_PRODUCT_FILTERS.setCategories(filterCategories);
    PRODUCTS.add(A_PRODUCT_WITH_TWO_CATEGORIES);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(A_PRODUCT_WITH_TWO_CATEGORIES), actual);
  }

  @Test
  public void givenAProductWithTwoCategoriesAndTwoThemCategoriesAsAFilter_whenFindFilteredProducts_thenShouldFindProduct() {
    Categories filterCategories = new Categories(List.of(A_CATEGORY, ANOTHER_CATEGORY));
    A_PRODUCT_FILTERS.setCategories(filterCategories);
    PRODUCTS.add(A_PRODUCT_WITH_TWO_CATEGORIES);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(A_PRODUCT_WITH_TWO_CATEGORIES), actual);
  }

  @Test
  public void givenAProductWithTwoCategoriesAndADifferentCategoryAsAFilter_whenFindFilteredProducts_thenShouldNotFindProduct() {
    Category aDifferentCategory = new Category("OHOH");
    Categories filterCategories = new Categories(List.of(aDifferentCategory));
    A_PRODUCT_FILTERS.setCategories(filterCategories);
    PRODUCTS.add(A_PRODUCT_WITH_TWO_CATEGORIES);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertTrue(actual.isEmpty());
  }

  @Test
  public void givenAProductAndAFilterWithAPartOfTheProductTitleAMinimumPriceLowerThanProductPriceAndACategoryCorrespondingToProduct_whenFindFilteredProducts_thenShouldFindProduct() {
    Categories productCategories = new Categories(List.of(A_CATEGORY, ANOTHER_CATEGORY));
    Product aProduct = new ProductBuilder().withTitle(A_PRODUCT_TITLE).withAmount(AN_AMOUNT).withCategories(productCategories).build();
    Categories filterCategories = new Categories(List.of(A_CATEGORY));
    A_PRODUCT_FILTERS.setCategories(filterCategories);
    PRODUCTS.add(aProduct);
    givenProducts(PRODUCTS);

    List<Product> actual = this.productFilterer.findFilteredProducts(A_PRODUCT_FILTERS);

    assertEquals(List.of(aProduct), actual);
  }


  private void givenProducts(List<Product> products) {
    given(this.productRepository.findAll()).willReturn(products);
  }
}
