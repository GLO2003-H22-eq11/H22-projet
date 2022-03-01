package ulaval.glo2003.e2e.success;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.product.api.response.ProductsResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.success.ProductEnd2EndTestUtils.getProductsWithNonExistentValuesAsFilters;
import static ulaval.glo2003.e2e.success.SellerEnd2EndTestUtils.createSellerWithProduct;

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

  @Test
  public void givenAProduct_whenGetProductsWithNonExistentValues_thenShouldNotReturnAnyProducts() {
    createSellerWithProduct();

    ProductsResponse response = getProductsWithNonExistentValuesAsFilters();

    assertTrue(response.products.isEmpty());
  }
}
