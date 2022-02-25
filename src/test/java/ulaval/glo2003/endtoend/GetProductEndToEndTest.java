package ulaval.glo2003.endtoend;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.api.ProductRequest;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.seller.api.SellerRequest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetProductEndToEndTest extends EndToEndConfig {

  @Test
  public void givenAProductRequest_whenGetProduct_thenShouldReturnRightStatusCode() {
    String productId = addProductAndGetProductId();

    given()
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(this.URL_PRODUCTS_END_POINT + productId)
            .then().assertThat().statusCode(this.GET_STATUS_CODE);

  }

  @Test
  public void givenAProductRequest_whenGetProduct_thenShouldTheRightBody() {
    String productId = addProductAndGetProductId();

    ProductResponse productResponse = given()
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(this.URL_PRODUCTS_END_POINT + productId)
            .getBody().as(ProductResponse.class);

    assertEquals(productResponse.id, productId);
    assertEquals(productResponse.title, this.A_PRODUCT_TITLE);
    assertEquals(productResponse.suggestedPrice, this.A_VALID_SUGGESTED_PRICE);
    assertEquals(productResponse.description, this.A_PRODUCT_DESCRIPTION);
    assertEquals(productResponse.categories, this.A_CATEGORIES);
  }

  private String addProductAndGetProductId() {
    ProductRequest productRequest = this.givenAProductRequest();
    String sellerId = addSellerAndGetSellerId();

    String URL_LOCATION = given()
            .body(productRequest)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(this.X_SELLER_ID_HEADERS_PARAMS, sellerId)
            .post(this.PRODUCTS_END_POINT).getHeader(this.LOCATION);

    return URL_LOCATION.split("products/")[1];
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
