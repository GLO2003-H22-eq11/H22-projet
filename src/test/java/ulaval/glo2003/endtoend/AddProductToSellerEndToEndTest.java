package ulaval.glo2003.endtoend;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.api.ProductRequest;
import ulaval.glo2003.seller.api.SellerRequest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddProductToSellerEndToEndTest extends EndToEndConfig {

  @Test
  public void givenAProductRequest_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    String sellerId = addSellerAndGetSellerId();

    ProductRequest productRequest = this.givenAProductRequest();

    given()
            .body(productRequest)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(this.X_SELLER_ID_HEADERS_PARAMS, sellerId)
            .post(this.PRODUCTS_END_POINT)
            .then().assertThat().statusCode(this.NO_CONTENT_STATUS_CODE);

  }

  @Test
  public void givenAProductRequest_whenAddProduct_thenShouldReturnAProductId() {
    String sellerId = addSellerAndGetSellerId();

    ProductRequest productRequest = this.givenAProductRequest();

    String URL_LOCATION = given()
            .body(productRequest)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(this.X_SELLER_ID_HEADERS_PARAMS, sellerId)
            .post(this.PRODUCTS_END_POINT).getHeader(this.LOCATION);

    String productId = URL_LOCATION.split("products/")[1];

    assertTrue(URL_LOCATION.contains(this.URL_PRODUCTS_END_POINT));
    assertTrue(productId.matches(this.UUID_REGEX));
  }

  private String addSellerAndGetSellerId() {
    SellerRequest sellerRequest = this.givenAValidSellerRequest();

    RequestSpecification request = given();
    request.header(CONTENT_TYPE, APPLICATION_JSON);
    request.body(sellerRequest);

    String URL_LOCATION  = request.post(this.SELLER_END_POINT).getHeaders()
            .getValue(this.LOCATION);
    return URL_LOCATION.split("sellers/")[1];
  }


}
