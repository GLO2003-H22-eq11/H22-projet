package ulaval.glo2003.e2e;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.product.api.ProductRequest;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.seller.api.SellerRequest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.createProductGetId;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.getProduct;
import static ulaval.glo2003.e2e.SellerEnd2EndTestUtils.createSellerGetId;

public class GetProductEnd2EndTest extends End2EndConfig {

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
