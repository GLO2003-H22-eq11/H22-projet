package ulaval.glo2003.e2e.success;

import io.restassured.response.Response;
import ulaval.glo2003.product.api.ProductRequest;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.product.api.response.ProductsResponse;

import java.util.List;

import static io.restassured.RestAssured.given;
import static ulaval.glo2003.e2e.End2EndConfig.APPLICATION_JSON;
import static ulaval.glo2003.e2e.End2EndConfig.CONTENT_TYPE;
import static ulaval.glo2003.e2e.End2EndConfig.LOCATION;
import static ulaval.glo2003.e2e.End2EndConfig.PRODUCTS_END_POINT;
import static ulaval.glo2003.e2e.End2EndConfig.URL_PRODUCTS_END_POINT;
import static ulaval.glo2003.e2e.End2EndConfig.URL_PRODUCTS_END_POINT_WITHOUT_SLASH;
import static ulaval.glo2003.e2e.End2EndConfig.X_SELLER_ID_HEADERS_PARAMS;

public class ProductEnd2EndTestUtils {

  private static final String A_PRODUCT_TITLE = "TITLE";
  private static final String A_PRODUCT_DESCRIPTION = "description";
  private static final int A_VALID_SUGGESTED_PRICE = 10;
  private static final int A_VALID_MINIMUM_PRICE = 2;
  private static final int A_VALID_MAXIMUM_PRICE = 50;
  private static final List<String> A_CATEGORIES = List.of("A", "B", "C");

  private static final String A_RANDOM_TITLE = "random";
  private static final int A_NON_VALID_MIN = 200;
  private static final int A_NON_VALID_MAX = 800;
  private static final String A_RANDOM_CATEGORY = "random";

  public static Response createProduct(String sellerId) {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = A_PRODUCT_DESCRIPTION;
    productRequest.title = A_PRODUCT_TITLE;
    productRequest.suggestedPrice = A_VALID_SUGGESTED_PRICE;
    productRequest.categories = A_CATEGORIES;

    return given()
            .body(productRequest)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(X_SELLER_ID_HEADERS_PARAMS, sellerId)
            .post(PRODUCTS_END_POINT);
  }

  public static String createProductGetId(String sellerId) {
    Response response = createProduct(sellerId);
    String locationHeader = response.getHeader(LOCATION);
    return locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
  }

  public static ProductResponse getProduct(String productId) {
    return given()
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(URL_PRODUCTS_END_POINT + productId)
            .getBody().as(ProductResponse.class);
  }

  public static ProductsResponse getProductsWithValidFilters(String sellerId) {
    return given()
            .queryParam("title", A_PRODUCT_TITLE)
            .queryParam("sellerId", sellerId)
            .queryParam("categories", A_CATEGORIES)
            .queryParam("minPrice", A_VALID_MINIMUM_PRICE)
            .queryParam("maxPrice", A_VALID_MAXIMUM_PRICE)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(URL_PRODUCTS_END_POINT_WITHOUT_SLASH).getBody().as(ProductsResponse.class);

  }

  public static ProductsResponse getProductsWithNonExistentValuesAsFilters() {
    return given()
            .queryParam("title", A_RANDOM_TITLE)
            .queryParam("categories", A_RANDOM_CATEGORY)
            .queryParam("minPrice", A_NON_VALID_MIN)
            .queryParam("maxPrice", A_NON_VALID_MAX)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(URL_PRODUCTS_END_POINT_WITHOUT_SLASH).getBody().as(ProductsResponse.class);
  }
}
