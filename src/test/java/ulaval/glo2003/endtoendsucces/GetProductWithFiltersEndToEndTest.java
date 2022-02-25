package ulaval.glo2003.endtoendsucces;

import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.api.ProductRequest;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.product.api.response.ProductsResponse;
import ulaval.glo2003.seller.api.SellerRequest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetProductWithFiltersEndToEndTest extends EndToEndConfig {

  @Test
  public void givenAProductRequest_whenGetProductWithFilters_thenShouldReturnTheRightStatusCode() {
    String sellerId = addSellerAndGetSellerId();
    addProductToSeller(sellerId);

    given()
            .queryParam("title", this.A_PRODUCT_TITLE)
            .queryParam("sellerId", sellerId)
            .queryParam("categories", this.A_CATEGORIES)
            .queryParam("minPrice", this.A_VALID_SUGGESTED_PRICE)
            .queryParam("maxPrice", this.A_VALID_SUGGESTED_PRICE)
            .header("Content-Type", "application/json")
            .get(this.URL_PRODUCTS_END_POINT_WITHOUT_SLASH)
            .then().assertThat().statusCode(this.GET_STATUS_CODE);
  }

  @Test
  public void givenAProductRequest_whenGetProductWithFilters_thenShouldReturnTheRightBody() {
    String sellerId = addSellerAndGetSellerId();
    addProductToSeller(sellerId);

    ProductsResponse productsResponse = given()
            .queryParam("title", this.A_PRODUCT_TITLE)
            .queryParam("sellerId", sellerId)
            .queryParam("categories", this.A_CATEGORIES)
            .queryParam("minPrice", this.A_VALID_SUGGESTED_PRICE)
            .queryParam("maxPrice", this.A_VALID_SUGGESTED_PRICE)
            .header("Content-Type", "application/json")
            .get(this.URL_PRODUCTS_END_POINT_WITHOUT_SLASH)
            .getBody().as(ProductsResponse.class);

    ProductResponse productResponse = productsResponse.products.get(0);

    assertEquals(productResponse.title, this.A_PRODUCT_TITLE);
    assertEquals(productResponse.seller.id, sellerId);
    assertEquals(productResponse.categories, this.A_CATEGORIES);
    assertEquals(productResponse.suggestedPrice, this.A_VALID_SUGGESTED_PRICE);

  }

  private void addProductToSeller(String sellerId) {
    ProductRequest productRequest = this.givenAProductRequest();

    given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header(this.X_SELLER_ID_HEADERS_PARAMS, sellerId)
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
