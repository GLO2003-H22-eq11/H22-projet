package ulaval.glo2003.e2e.failure;

import io.restassured.response.Response;
import ulaval.glo2003.product.api.request.ProductRequest;

import static io.restassured.RestAssured.given;
import static ulaval.glo2003.e2e.End2EndConfig.*;

public class ProductEnd2EndTestUtils {

  public static ProductRequest givenAProductRequestWithoutCategories() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = A_PRODUCT_DESCRIPTION;
    productRequest.title = A_PRODUCT_TITLE;
    productRequest.suggestedPrice = A_VALID_SUGGESTED_PRICE;

    return productRequest;
  }

  public static ProductRequest givenAProductRequestWithoutDescription() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.title = A_PRODUCT_TITLE;
    productRequest.suggestedPrice = A_VALID_SUGGESTED_PRICE;
    productRequest.categories = A_CATEGORIES;

    return productRequest;
  }

  public static ProductRequest givenAProductRequestWithoutTitle() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = A_PRODUCT_DESCRIPTION;
    productRequest.suggestedPrice = A_VALID_SUGGESTED_PRICE;
    productRequest.categories = A_CATEGORIES;

    return productRequest;
  }

  public static ProductRequest givenAProductRequestWithoutPrice() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = A_PRODUCT_DESCRIPTION;
    productRequest.title = A_PRODUCT_TITLE;
    productRequest.categories = A_CATEGORIES;
    productRequest.suggestedPrice = null;

    return productRequest;
  }

  public static ProductRequest givenAProductRequestWithBadPrice() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = A_PRODUCT_DESCRIPTION;
    productRequest.title = A_PRODUCT_TITLE;
    productRequest.categories = A_CATEGORIES;
    productRequest.suggestedPrice = 0.0;

    return productRequest;
  }

  public static ProductRequest givenAProductRequest() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = A_PRODUCT_DESCRIPTION;
    productRequest.title = A_PRODUCT_TITLE;
    productRequest.suggestedPrice = A_VALID_SUGGESTED_PRICE;
    productRequest.categories = A_CATEGORIES;

    return productRequest;
  }

  public static Response getProductWithStringFilters(String queryParamName, String param) {
    return given()
            .queryParam(queryParamName, param)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(PRODUCTS_END_POINT);
  }

  public static Response getProductWithIntFilters(String queryParamName, Double param) {
    return given()
            .queryParam(queryParamName, param)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(PRODUCTS_END_POINT);
  }

  public static Response postProductWithBody(ProductRequest productRequest, String sellerId) {
    return given()
            .body(productRequest)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(X_SELLER_ID_HEADERS_PARAMS, sellerId)
            .post(URL_PRODUCTS_END_POINT);
  }

  public static Response getProductWithProductId(String productId) {
    return given()
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(URL_PRODUCTS_END_POINT + "/" + productId);
  }
}
