package ulaval.glo2003.e2e;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.seller.api.response.SellerWithProductsResponse;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.*;
import static ulaval.glo2003.e2e.SellerEnd2EndTestUtils.*;

public class GetCurrentSellerEndToEndTest {

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
  public void givenASellerId_whenGetCurrentSellerId_thenShouldReturn200StatusCode() {
    String sellerId = createSellerAndGetId();

    Response response = getCurrentSellerResponse(sellerId);

    response.then().assertThat().statusCode(OK_STATUS_CODE);
  }

  @Test
  public void givenASellerId_whenGetCurrentSellerId_thenShouldReturnBodyWithoutProducts() {
    String sellerId = createSellerAndGetId();

    SellerWithProductsResponse sellerWithProductsResponse = getCurrentSellerBody(sellerId);

    assertEquals(sellerWithProductsResponse.id, sellerId);
    assertEquals(sellerWithProductsResponse.bio, A_BIO);
    assertEquals(sellerWithProductsResponse.name, A_SELLER_NAME);
    assertEquals(sellerWithProductsResponse.birthDate, A_SELLER_DATE);
    assertEquals(sellerWithProductsResponse.products.size(), 0);
  }

  @Test
  public void givenASellerId_whenGetCurrentSellerId_thenShouldReturnBodyWithProducts() {
    String sellerId = createSellerAndGetId();
    String productId = createProductAndGetId(sellerId);

    SellerWithProductsResponse sellerWithProductsResponse = getCurrentSellerBody(sellerId);

    assertEquals(sellerWithProductsResponse.id, sellerId);
    assertEquals(sellerWithProductsResponse.bio, A_BIO);
    assertEquals(sellerWithProductsResponse.name, A_SELLER_NAME);
    assertEquals(sellerWithProductsResponse.birthDate, A_SELLER_DATE);
    assertEquals(sellerWithProductsResponse.products.size(), 1);
    assertEquals(sellerWithProductsResponse.products.get(0).id, productId);
  }

  @Test
  public void givenASellerId_whenGetCurrentSellerId_thenShouldReturnBodyWithProductsAndOffers() {
    String sellerId = createSellerAndGetId();
    createProductAndOffer(sellerId);

    SellerWithProductsResponse sellerWithProductsResponse = getCurrentSellerBody(sellerId);

    assertEquals(sellerWithProductsResponse.id, sellerId);
    assertEquals(sellerWithProductsResponse.bio, A_BIO);
    assertEquals(sellerWithProductsResponse.name, A_SELLER_NAME);
    assertEquals(sellerWithProductsResponse.birthDate, A_SELLER_DATE);
    assertEquals(sellerWithProductsResponse.products.size(), 1);
    assertEquals(sellerWithProductsResponse.products.get(0).offers.count, 1);
  }

  @Test
  public void givenANonExistingSellerId_whenGetSeller_thenShouldReturn404StatusCode() {
    getCurrentSellerResponse(A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(NOT_FOUND_STATUS_CODE);

  }

  @Test
  public void givenABadSellerId_whenGetSeller_thenShouldReturn400StatusCode() {
    getCurrentSellerResponse(A_NON_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);

  }

  @Test
  public void givenABadSellerId_whenGetSeller_thenShouldReturnItemNotFoundBody() {
    getCurrentSellerResponse(A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_ITEM_NOT_FOUND))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_ITEM_NOT_FOUND_DESCRIPTION));

  }

  @Test
  public void givenANonExistingSellerId_whenGetSeller_thenShouldReturnInvalidParameterBody() {
    getCurrentSellerResponse(A_NON_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));

  }
}
