package ulaval.glo2003.e2e;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.product.api.response.ProductWithSellerResponse;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.End2EndConfig.AN_ITEM_NOT_FOUND_DESCRIPTION;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.*;
import static ulaval.glo2003.e2e.SellerEnd2EndTestUtils.createSellerGetId;

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

  @AfterAll
  public static void clearDatabase() {
    ProductEnd2EndTestUtils.clearProductsDatabase();
    SellerEnd2EndTestUtils.clearSellersDatabase();
  }

  @Test
  public void givenAProductRequest_whenGetProduct_thenShouldReturn200StatusCode() {
    String sellerId = createSellerGetId();
    String productId = createProductAndGetId(sellerId);

    int statusCode = getProductResponse(productId).statusCode();

    assertEquals(OK_STATUS_CODE, statusCode);
  }

  @Test
  public void givenAProductRequest_whenGetProduct_thenShouldReturnTheRightBody() {
    String sellerId = createSellerGetId();
    String productId = createProductAndGetId(sellerId);

    ProductWithSellerResponse productWithSellerResponse = getProductResponseBody(productId);

    assertEquals(productWithSellerResponse.id, productId);
    assertEquals(productWithSellerResponse.title, A_PRODUCT_TITLE);
    assertEquals(productWithSellerResponse.suggestedPrice, A_VALID_SUGGESTED_PRICE);
    assertEquals(productWithSellerResponse.description, A_PRODUCT_DESCRIPTION);
    assertEquals(productWithSellerResponse.categories, A_CATEGORIES);
  }

  @Test
  public void givenAProductRequestWithBadId_whenGetProduct_thenShouldReturn400StatusCode() {
    getProductWithProductId(A_NON_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);

  }

  @Test
  public void givenAProductRequestWithNonExistingId_whenGetProduct_thenShouldReturn404StatusCode() {
    getProductWithProductId(A_VALID_NON_EXISTING_UUID_FORMAT)
            .then().assertThat().statusCode(NOT_FOUND_STATUS_CODE);

  }

  @Test
  public void givenAProductRequestWithBadId_whenGetProduct_thenShouldReturnInvalidParameterBody() {
    getProductWithProductId(A_NON_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));

  }

  @Test
  public void givenAProductRequestWithNonExistingId_whenGetProduct_thenShouldReturnItemNotFoundBody() {
    getProductWithProductId(A_VALID_NON_EXISTING_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_ITEM_NOT_FOUND))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_ITEM_NOT_FOUND_DESCRIPTION));

  }
}
