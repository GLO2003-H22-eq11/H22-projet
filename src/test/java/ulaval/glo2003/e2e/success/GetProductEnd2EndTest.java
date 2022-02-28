package ulaval.glo2003.e2e.success;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.product.api.response.ProductResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.A_CATEGORIES;
import static ulaval.glo2003.e2e.End2EndConfig.A_PRODUCT_DESCRIPTION;
import static ulaval.glo2003.e2e.End2EndConfig.A_PRODUCT_TITLE;
import static ulaval.glo2003.e2e.End2EndConfig.A_VALID_SUGGESTED_PRICE;
import static ulaval.glo2003.e2e.success.ProductEnd2EndTestUtils.createProductGetId;
import static ulaval.glo2003.e2e.success.ProductEnd2EndTestUtils.getProduct;
import static ulaval.glo2003.e2e.success.SellerEnd2EndTestUtils.createSellerGetId;

public class GetProductEnd2EndTest {

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
  public void givenAProductRequest_whenGetProduct_thenShouldReturnTheRightBody() {
    String sellerId = createSellerGetId();
    String productId = createProductGetId(sellerId);

    ProductResponse productResponse = getProduct(productId);

    assertEquals(productResponse.id, productId);
    assertEquals(productResponse.title, A_PRODUCT_TITLE);
    assertEquals(productResponse.suggestedPrice, A_VALID_SUGGESTED_PRICE);
    assertEquals(productResponse.description, A_PRODUCT_DESCRIPTION);
    assertEquals(productResponse.categories, A_CATEGORIES);
  }
}
