package ulaval.glo2003.e2e.success;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.product.api.response.ProductsResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.A_CATEGORIES;
import static ulaval.glo2003.e2e.End2EndConfig.A_PRODUCT_DESCRIPTION;
import static ulaval.glo2003.e2e.End2EndConfig.A_PRODUCT_TITLE;
import static ulaval.glo2003.e2e.End2EndConfig.A_VALID_SUGGESTED_PRICE;
import static ulaval.glo2003.e2e.End2EndConfig.OK_STATUS_CODE;
import static ulaval.glo2003.e2e.success.ProductEnd2EndTestUtils.getProductsResponseBodyWithValidFilters;
import static ulaval.glo2003.e2e.success.ProductEnd2EndTestUtils.getProductsResponseWithValidFilters;
import static ulaval.glo2003.e2e.success.SellerEnd2EndTestUtils.createSellerWithProductAndGetSellerId;

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

    ProductsResponse productsResponse = getProductsResponseBodyWithValidFilters(sellerId);
    ProductResponse productResponse = productsResponse.products.get(0);

    assertEquals(productResponse.title, A_PRODUCT_TITLE);
    assertEquals(productResponse.seller.id, sellerId);
    assertEquals(productResponse.categories, A_CATEGORIES);
    assertEquals(productResponse.description, A_PRODUCT_DESCRIPTION);
    assertEquals(productResponse.suggestedPrice, A_VALID_SUGGESTED_PRICE);
  }
}
