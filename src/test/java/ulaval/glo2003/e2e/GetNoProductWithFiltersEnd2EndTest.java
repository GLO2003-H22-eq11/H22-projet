package ulaval.glo2003.e2e;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.product.api.ProductRequest;
import ulaval.glo2003.product.api.response.ProductsResponse;
import ulaval.glo2003.seller.api.SellerRequest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static spark.Spark.stop;

public class GetNoProductWithFiltersEnd2EndTest extends End2EndConfig {

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


//  @Test
//  public void givenAProductRequest_whenGetNoProductWithFilters_thenShouldReturnTheRightStatusCode() {
//    String sellerId = addSellerAndGetSellerId();
//    addProductToSeller(sellerId);
//
//    given()
//            .queryParam("title", A_RANDOM_TITLE)
//            .queryParam("sellerId", A_RANDOM_ID)
//            .queryParam("categories", A_RANDOM_CATEGORIE)
//            .queryParam("minPrice", A_RANDOM_MIN)
//            .queryParam("maxPrice", A_RANDOM_MAX)
//            .header(CONTENT_TYPE, APPLICATION_JSON)
//            .get(this.URL_PRODUCTS_END_POINT_WITHOUT_SLASH)
//            .then().assertThat().statusCode(this.OK_STATUS_CODE);
//  }
//
//  @Test
//  public void givenAProductRequest_whenGetNoProductWithFilters_thenShouldReturnTheRightBody() {
//    String sellerId = addSellerAndGetSellerId();
//    addProductToSeller(sellerId);
//
//    ProductsResponse productsResponse = given()
//            .queryParam("title", A_RANDOM_TITLE)
//            .queryParam("sellerId", A_RANDOM_ID)
//            .queryParam("categories", A_RANDOM_CATEGORIE)
//            .queryParam("minPrice", A_RANDOM_MIN)
//            .queryParam("maxPrice", A_RANDOM_MAX)
//            .header(CONTENT_TYPE, APPLICATION_JSON)
//            .get(this.URL_PRODUCTS_END_POINT_WITHOUT_SLASH)
//            .getBody().as(ProductsResponse.class);
//
//    assertTrue(productsResponse.products.isEmpty());
//  }

  private void addProductToSeller(String id) {
    ProductRequest productRequest = this.givenAProductRequest();

    given()
            .body(productRequest)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(this.X_SELLER_ID_HEADERS_PARAMS, id)
            .post(this.PRODUCTS_END_POINT).getHeader(this.LOCATION);
  }

  private String addSellerAndGetSellerId() {
    SellerRequest sellerRequest = this.givenAValidSellerRequest();

    RequestSpecification request = given();
    request.header(CONTENT_TYPE, APPLICATION_JSON);
    request.body(sellerRequest);

    String URL_LOCATION = request.post(this.SELLER_END_POINT).getHeaders()
            .getValue(this.LOCATION);
    return URL_LOCATION.split("sellers/")[1];
  }
}
