package ulaval.glo2003.e2e;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.product.api.response.ProductWithViewsResponse;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.*;
import static ulaval.glo2003.e2e.SellerEnd2EndTestUtils.createSellerAndGetId;

public class GetViewsEndToEndTest {

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
  public void givenASerllerId_whenGetViews_thenShouldReturn200StatusCode(){
    String sellerId = createSellerAndGetId();
    String productId = createProductAndGetId(sellerId);

    getProductResponse(productId);

    Response response = getProductViews(sellerId);

    response.then().assertThat().statusCode(OK_STATUS_CODE);
  }

  @Test
  public void givenASerllerId_whenGetViews_thenShouldReturnMatchingBody(){
    Integer expectedNumbersOfViews = 1;
    String sellerId = createSellerAndGetId();
    String productId = createProductAndGetId(sellerId);

    getProductResponse(productId);

    List<ProductWithViewsResponse> productWithViewsResponse = getProductsWithViewsResponseBody(sellerId);

    assertEquals(productWithViewsResponse.get(0).id, productId);
    assertEquals(productWithViewsResponse.get(0).title, A_PRODUCT_TITLE);
    assertEquals(productWithViewsResponse.get(0).suggestedPrice, A_VALID_SUGGESTED_PRICE);
    assertEquals(productWithViewsResponse.get(0).description, A_PRODUCT_DESCRIPTION);
    assertEquals(productWithViewsResponse.get(0).categories, A_CATEGORIES);
    assertEquals(productWithViewsResponse.get(0).views, expectedNumbersOfViews);
  }

  @Test
  public void givenASerllerId_whenGetViews_thenShouldReturn400StatusCode(){
    String sellerId = createSellerAndGetId();
    String productId = createProductAndGetId(sellerId);

    getProductResponse(productId);

    Response response = getProductViews(A_NON_VALID_UUID_FORMAT);

    response.then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenASerllerId_whenGetViews_thenShouldReturnInvalidParameterBody(){
    String sellerId = createSellerAndGetId();
    String productId = createProductAndGetId(sellerId);

    getProductResponse(productId);

    Response response = getProductViews(A_NON_VALID_UUID_FORMAT);

    response.then().assertThat()
            .body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }
}
