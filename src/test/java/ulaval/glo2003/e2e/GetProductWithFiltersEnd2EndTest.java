package ulaval.glo2003.e2e;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.product.api.response.ProductWithSellerResponse;
import ulaval.glo2003.product.api.response.ProductsWithSellerResponse;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.*;
import static ulaval.glo2003.e2e.SellerEnd2EndTestUtils.createSellerWithProductAndGetSellerId;

public class GetProductWithFiltersEnd2EndTest {

  @BeforeAll
  public static void startServer() {
    try {
      ApplicationMain.main(new String[0]);

    } catch (Exception ignored) {
    }
  }

  @AfterAll
  public static void stopServer() {
    stop();
  }

  @AfterAll
  public static void clearDatabase() {
    ProductEnd2EndTestUtils.clearProductsDatabase();
    SellerEnd2EndTestUtils.clearSellersDatabase();
  }


  @Test
  public void givenAProduct_whenGetProductWithInclusiveFiltersThatMatch_thenShouldReturn200StatusCode() {
    String sellerId = createSellerWithProductAndGetSellerId();

    int statusCode = getProductsResponseWithValidFilters(sellerId).statusCode();

    assertEquals(OK_STATUS_CODE, statusCode);
  }


  @Test
  public void givenAProduct_whenGetProductWithInclusiveFiltersThatMatch_thenShouldReturnProduct() {
    String sellerId = createSellerWithProductAndGetSellerId();

    ProductsWithSellerResponse productsWithSellerResponse = getProductsResponseBodyWithValidFilters(sellerId);
    ProductWithSellerResponse productWithSellerResponse = productsWithSellerResponse.products.get(0);

    assertEquals(productWithSellerResponse.title, A_PRODUCT_TITLE);
    assertEquals(productWithSellerResponse.seller.id, sellerId);
    assertEquals(productWithSellerResponse.categories, A_CATEGORIES);
    assertEquals(productWithSellerResponse.description, A_PRODUCT_DESCRIPTION);
    assertEquals(productWithSellerResponse.suggestedPrice, A_VALID_SUGGESTED_PRICE);
  }

  @Test
  public void givenAProduct_whenGetProductWithInvalidSellerIdFilter_thenShouldReturn400StatusCode() {
    getProductWithStringFilters(SELLER_ID_PARAM, A_NON_VALID_UUID_FORMAT).then().assertThat().statusCode(BAD_STATUS_CODE);

  }

  @Test
  public void givenAProduct_whenGetProductWithNonExistingSellerIdFilter_thenShouldReturn404StatusCode() {
    getProductWithStringFilters(SELLER_ID_PARAM, A_VALID_NON_EXISTING_UUID_FORMAT).then().assertThat().statusCode(NOT_FOUND_STATUS_CODE);
  }

  @Test
  public void givenAProduct_whenGetProductWithNegativeMinPriceFilter_thenShouldReturn400StatusCode() {
    getProductWithIntFilters(MIN_PRICE_PARAM, A_NEGATIVE_PRICE).then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProduct_whenGetProductWithNegativeMaxPriceFilter_thenShouldReturn400StatusCode() {
    getProductWithIntFilters(MAX_PRICE_PARAM, A_NEGATIVE_PRICE).then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProduct_whenGetProductWithInvalidSellerIdFilter_thenShouldReturnInvalidParameterBody() {
    getProductWithStringFilters(SELLER_ID_PARAM, A_NON_VALID_UUID_FORMAT).then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER)).body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProduct_whenGetProductWithNonExistingSellerIdFilter_thenShouldReturnItemNotFoundBody() {
    getProductWithStringFilters(SELLER_ID_PARAM, A_VALID_NON_EXISTING_UUID_FORMAT).then().body(AN_ERROR, equalTo(AN_ITEM_NOT_FOUND)).body(AN_ERROR_DESCRIPTION, equalTo(AN_ITEM_NOT_FOUND_DESCRIPTION));
  }

  @Test
  public void givenAProduct_whenGetProductWithNegativeMinPriceFilter_thenShouldReturnInvalidParameterBody() {
    getProductWithIntFilters(MIN_PRICE_PARAM, A_NEGATIVE_PRICE).then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER)).body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProduct_whenGetProductWithNegativeMaxPriceFilter_thenShouldReturnInvalidParameterBody() {
    getProductWithIntFilters(MAX_PRICE_PARAM, A_NEGATIVE_PRICE).then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER)).body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }
}
