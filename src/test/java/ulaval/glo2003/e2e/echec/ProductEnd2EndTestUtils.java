package ulaval.glo2003.e2e.echec;

import ulaval.glo2003.product.api.ProductRequest;

import static io.restassured.RestAssured.given;
import static ulaval.glo2003.e2e.End2EndConfig.APPLICATION_JSON;
import static ulaval.glo2003.e2e.End2EndConfig.A_CATEGORIES;
import static ulaval.glo2003.e2e.End2EndConfig.A_PRODUCT_DESCRIPTION;
import static ulaval.glo2003.e2e.End2EndConfig.A_PRODUCT_TITLE;
import static ulaval.glo2003.e2e.End2EndConfig.A_VALID_SUGGESTED_PRICE;
import static ulaval.glo2003.e2e.End2EndConfig.CONTENT_TYPE;
import static ulaval.glo2003.e2e.End2EndConfig.LOCATION;
import static ulaval.glo2003.e2e.End2EndConfig.PRODUCTS_END_POINT;
import static ulaval.glo2003.e2e.End2EndConfig.X_SELLER_ID_HEADERS_PARAMS;

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

  public static ProductRequest givenAProductRequest() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = A_PRODUCT_DESCRIPTION;
    productRequest.title = A_PRODUCT_TITLE;
    productRequest.suggestedPrice = A_VALID_SUGGESTED_PRICE;
    productRequest.categories = A_CATEGORIES;

    return productRequest;
  }
  private String addProductAndGetProductId(String sellerId) {
    ProductRequest productRequest = this.givenAProductRequest();

    String URL_LOCATION = given()
      .body(productRequest)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(X_SELLER_ID_HEADERS_PARAMS, sellerId)
      .post(PRODUCTS_END_POINT).getHeader(LOCATION);

    return URL_LOCATION.split(PRODUCTS_END_POINT)[1];
  }

  private void addProductToSeller(String sellerId) {
    ProductRequest productRequest = this.givenAProductRequest();

    given()
      .body(productRequest)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(X_SELLER_ID_HEADERS_PARAMS, sellerId)
      .post(PRODUCTS_END_POINT).getHeader(LOCATION);
  }
}
