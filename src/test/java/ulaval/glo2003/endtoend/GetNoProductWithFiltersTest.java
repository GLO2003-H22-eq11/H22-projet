package ulaval.glo2003.endtoend;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.api.ProductRequest;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.product.api.response.ProductsResponse;
import ulaval.glo2003.seller.api.SellerRequest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetNoProductWithFiltersTest extends EndToEndConfig {

  @Test
  public void givenAProductRequest_whenGetNoProductWithFilters_thenShouldReturnTheRightStatusCode() {
    String sellerId = addSellerAndGetSellerId();
    addProductToSeller(sellerId);

    given()
            .queryParam("title", A_RANDOM_TITLE)
            .queryParam("sellerId", "")
            .queryParam("categories", "")
            .queryParam("minPrice", "")
            .queryParam("maxPrice", "")
            .header("Content-Type", "application/json")
            .get(this.URL_PRODUCTS_END_POINT_WITHOUT_SLASH)
            .then().assertThat().statusCode(this.GET_STATUS_CODE);
  }

  @Test
  public void givenAProductRequest_whenGetNoProductWithFilters_thenShouldReturnTheRightBody() {
    String sellerId = addSellerAndGetSellerId();
    addProductToSeller(sellerId);

    ProductsResponse productsResponse = given()
            .queryParam("title", A_RANDOM_TITLE)
            .queryParam("sellerId", "")
            .queryParam("categories", "")
            .queryParam("minPrice", "")
            .queryParam("maxPrice", "")
            .header("Content-Type", "application/json")
            .get(this.URL_PRODUCTS_END_POINT_WITHOUT_SLASH)
            .getBody().as(ProductsResponse.class);

    assertTrue(productsResponse.products.isEmpty());
  }

  private void addProductToSeller(String id) {
    ProductRequest productRequest = this.givenAProductRequest();

    given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header(this.X_SELLER_ID_HEADERS_PARAMS, id)
            .post(this.PRODUCTS_END_POINT).getHeader(this.LOCATION);
  }

  private String addSellerAndGetSellerId() {
    SellerRequest sellerRequest = this.givenAValidSellerRequest();

    RequestSpecification request = given();
    request.header("Content-Type", "application/json");
    request.body(sellerRequest);

    String URL_LOCATION = request.post(this.SELLER_END_POINT).getHeaders()
            .getValue(this.LOCATION);
    return URL_LOCATION.split("sellers/")[1];
  }
}
