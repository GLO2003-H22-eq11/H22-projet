package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.seller.domain.SellerId;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductSorterTest {


  private final SellerId A_SELLER_ID = new SellerId();
  private final String A_PRODUCT_TITLE = "MARIN_TITLE";
  private final Amount THE_LOWEST_AMOUNT_POSSIBLE = Amount.fromInt(2);
  private final Amount A_AMOUNT = Amount.fromInt(30);
  private final Amount A_MAXIMAL_AMOUNT = Amount.fromInt(20);
  private final Amount A_MINIMAL_AMOUNT = Amount.fromInt(10);
  private ProductBuilder productBuilder = new ProductBuilder();

  private final Product A_PRODUCT_WITH_PRICE = productBuilder.withAmount(A_AMOUNT).build();
  private final Product A_PRODUCT_WITH_THE_LOWEST_AMOUNT = productBuilder.withAmount(THE_LOWEST_AMOUNT_POSSIBLE)
          .build();
  private final Product A_PRODUCT_WITH_NOTHING = productBuilder.build();
  private final Product A_PRODUCT_WITH_TITLE = productBuilder.withTitle(A_PRODUCT_TITLE).build();
  private final Product A_PRODUCT_WITH_SELLER_ID = productBuilder.withSellerId(A_SELLER_ID).build();


  @Test
  public void givenTwoProductsWithOneWithATitleAndAProductFilterWithTitle_whenSort_thenShouldReturnOnlyOneProduct() {
    List<Product> products = givenTwoProductsWithOneATitle();
    //ProductFilters productFilter = givenAnProductFilterWithTitle();

    //List<Product> actualProducts = this.productSorter.sortProduct(productFilter, products);
//
//    assertEquals(actualProducts.size(), 1);
//    assertEquals(actualProducts.get(0), A_PRODUCT_WITH_TITLE);
  }

  @Test
  public void givenTwoProductsWithOneWithAMinimalPriceAndAProductFilterWithAMaximalPrice_whenSort_thenShouldFilter() {
   // ProductFilters productFilter = givenAProductFilterWithMinimalPrice();
    List<Product> productsFilter = givenTwoProductsWithOnePrice();

   // List<Product> actualProducts = this.productSorter.sortProduct(productFilter, productsFilter);

//    assertEquals(actualProducts.size(), 1);
//    assertEquals(actualProducts.get(0), A_PRODUCT_WITH_PRICE);
  }

  @Test
  public void givenTwoProductsWithOneWithAMaximalPriceAndAProductFilterWithAMaximalPrice_whenSort_thenShouldFilter() {
    //ProductFilters productFilter = givenAProductFilterWithMaximalPrice();
    List<Product> productsFilter = givenTwoProductsWithOnePrice();

    //List<Product> actualProducts = this.productSorter.sortProduct(productFilter, productsFilter);

//    assertEquals(actualProducts.size(), 1);
//    assertEquals(actualProducts.get(0), A_PRODUCT_WITH_THE_LOWEST_AMOUNT);
  }

  @Test
  public void givenTwoProductsWithOneWIthASellerIdAndAProductFilterWIthSellerId_whenSort_thenShouldFilter() {
    //ProductFilters productFilter = givenAProductFilterWithSellerId();
    List<Product> productsFilter = givenTwoProductsWithOneSellerId();

    //List<Product> actualProducts = this.productSorter.sortProduct(productFilter, productsFilter);

//    assertEquals(actualProducts.size(), 1);
//    assertEquals(actualProducts.get(0), A_PRODUCT_WITH_SELLER_ID);
  }

  private List<Product> givenTwoProductsWithOneATitle() {
    List<Product> products = new LinkedList<>();
    products.add(A_PRODUCT_WITH_TITLE);
    products.add(A_PRODUCT_WITH_NOTHING);
    return products;
  }


  private List<Product> givenTwoProductsWithOnePrice() {
    List<Product> products = new LinkedList<>();
    products.add(A_PRODUCT_WITH_PRICE);
    products.add(A_PRODUCT_WITH_THE_LOWEST_AMOUNT);
    return products;
  }

  private List<Product> givenTwoProductsWithOneSellerId() {
    List<Product> products = new LinkedList<>();
    products.add(A_PRODUCT_WITH_SELLER_ID);
    products.add(A_PRODUCT_WITH_NOTHING);
    return products;
  }


//  public ProductFilters givenAnProductFilterWithTitle() {
//    ProductFilters productFilter = new ProductFilters(sellerId, title, categories, minimalPrice, maximumPrice);
//    productFilter.setTitle(A_PRODUCT_TITLE);
//    return productFilter;
//  }
//
//  public ProductFilters givenAProductFilterWithMinimalPrice() {
//    ProductFilters productFilter = new ProductFilters(sellerId, title, categories, minimalPrice, maximumPrice);
//    productFilter.setMinimalPrice(A_MINIMAL_AMOUNT);
//    return productFilter;
//  }
//
//  public ProductFilters givenAProductFilterWithMaximalPrice() {
//    ProductFilters productFilter = new ProductFilters(sellerId, title, categories, minimalPrice, maximumPrice);
//    productFilter.setMaximumPrice(A_MAXIMAL_AMOUNT);
//    return productFilter;
//  }
//
//  public ProductFilters givenAProductFilterWithSellerId() {
//    ProductFilters productFilter = new ProductFilters(sellerId, title, categories, minimalPrice, maximumPrice);
//    productFilter.setSellerId(A_SELLER_ID);
//    return productFilter;
//  }

}
