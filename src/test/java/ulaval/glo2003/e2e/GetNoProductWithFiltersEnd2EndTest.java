package ulaval.glo2003.e2e;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.product.api.response.ProductsResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.OK_STATUS_CODE;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.getProductsResponseBodyWithNonExistentValuesAsFilters;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.getProductsResponseWithNonExistentValuesAsFilters;
import static ulaval.glo2003.e2e.SellerEnd2EndTestUtils.createSellerWithProduct;

public class GetNoProductWithFiltersEnd2EndTest {

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
  public void givenAProduct_whenGetProductsWithNonExistentValues_thenShouldReturn200StatusCode() {
    createSellerWithProduct();

    int statusCode = getProductsResponseWithNonExistentValuesAsFilters().statusCode();

    assertEquals(OK_STATUS_CODE, statusCode);
  }

  @Test
  public void givenAProduct_whenGetProductsWithNonExistentValues_thenShouldNotReturnAnyProducts() {
    createSellerWithProduct();

    ProductsResponse response = getProductsResponseBodyWithNonExistentValuesAsFilters();

    assertTrue(response.products.isEmpty());
  }
}
