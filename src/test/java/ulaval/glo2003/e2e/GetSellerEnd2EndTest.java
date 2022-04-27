package ulaval.glo2003.e2e;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.seller.api.response.SellerResponse;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.SellerEnd2EndTestUtils.*;

public class GetSellerEnd2EndTest {

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
  public void givenASellerWithAProduct_whenGetSeller_thenShouldReturn200StatusCode() {
    String sellerId = createSellerWithProductAndGetSellerId();

    int statusCode = getSellerResponse(sellerId).statusCode();

    assertEquals(OK_STATUS_CODE, statusCode);
  }

  @Test
  public void givenASellerWithAProduct_whenGetSeller_thenShouldReturnTheRightBody() {
    String sellerId = createSellerWithProductAndGetSellerId();

    SellerResponse sellerResponse = getSellerResponseBody(sellerId);

    assertEquals(sellerResponse.id, sellerId);
    assertEquals(sellerResponse.bio, A_BIO);
    assertEquals(sellerResponse.name, A_SELLER_NAME);
    assertEquals(sellerResponse.products.size(), 1);
  }

  @Test
  public void givenANonExistingSellerId_whenGetSeller_thenShouldReturn404StatusCode() {
    getSellerWithSellerId(A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(NOT_FOUND_STATUS_CODE);

  }
  @Test
  public void givenABadSellerId_whenGetSeller_thenShouldReturn400StatusCode() {
    getSellerWithSellerId(A_NON_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);

  }

  @Test
  public void givenABadSellerId_whenGetSeller_thenShouldReturnItemNotFoundBody() {
    getSellerWithSellerId(A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_ITEM_NOT_FOUND))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_ITEM_NOT_FOUND_DESCRIPTION));

  }

  @Test
  public void givenANonExistingSellerId_whenGetSeller_thenShouldReturnInvalidParameterBody() {
    getSellerWithSellerId(A_NON_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));

  }

}
