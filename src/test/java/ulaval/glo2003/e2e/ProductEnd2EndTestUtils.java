package ulaval.glo2003.e2e;

import io.restassured.response.Response;
import ulaval.glo2003.product.api.request.OfferRequest;
import ulaval.glo2003.product.api.request.ProductRequest;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.product.api.response.ProductsResponse;

import static io.restassured.RestAssured.given;
import static ulaval.glo2003.e2e.End2EndConfig.*;

public class ProductEnd2EndTestUtils {

  private static final int A_VALID_MINIMUM_PRICE = 2;
  private static final int A_VALID_MAXIMUM_PRICE = 50;

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

  public static Response createOffer(String sellerId) {
    String productId = createProductAndGetId(sellerId);

    OfferRequest offerRequest = new OfferRequest();
    offerRequest.amount = A_VALID_AMOUNT;
    offerRequest.email = A_VALID_EMAIL;
    offerRequest.message = A_VALID_MESSAGE;
    offerRequest.phoneNumber = A_VALID_PHONE_NUMBER;
    offerRequest.name = A_VALID_NAME;

    return given()
            .body(offerRequest)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .post(PRODUCTS_END_POINT + "/" + productId + "/" + "offers");
  }

  public static String createProductAndGetId(String sellerId) {
    Response response = createProduct(sellerId);
    String locationHeader = response.getHeader(LOCATION);
    return locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
  }

  public static Response getProductResponse(String productId) {
    return given()
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(PRODUCTS_END_POINT + "/" + productId);
  }

  public static ProductResponse getProductResponseBody(String productId) {
    return getProductResponse(productId).getBody().as(ProductResponse.class);
  }

  public static Response getProductsResponseWithValidFilters(String sellerId) {
    return given()
            .queryParam("title", A_PRODUCT_TITLE)
            .queryParam("sellerId", sellerId)
            .queryParam("categories", A_CATEGORIES)
            .queryParam("minPrice", A_VALID_MINIMUM_PRICE)
            .queryParam("maxPrice", A_VALID_MAXIMUM_PRICE)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(PRODUCTS_END_POINT);

  }

  public static ProductsResponse getProductsResponseBodyWithValidFilters(String sellerId) {
    return getProductsResponseWithValidFilters(sellerId).getBody().as(ProductsResponse.class);
  }

  public static Response getProductsResponseWithNonExistentValuesAsFilters() {
    return given()
            .queryParam("title", A_RANDOM_TITLE)
            .queryParam("categories", A_RANDOM_CATEGORY)
            .queryParam("minPrice", A_NON_VALID_MIN)
            .queryParam("maxPrice", A_NON_VALID_MAX)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(PRODUCTS_END_POINT);
  }

  public static ProductsResponse getProductsResponseBodyWithNonExistentValuesAsFilters() {
    return getProductsResponseWithNonExistentValuesAsFilters().getBody().as(ProductsResponse.class);
  }

  public static void clearProductsDatabase() {
    given().delete(PRODUCTS_END_POINT + "/clear");
  }

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

  public static Response createOfferWithInvalidParams(String UUID, double amount, String email, String message,
                                                      String phoneNumber, String name) {
    String productId = UUID;

    OfferRequest offerRequest = new OfferRequest();
    offerRequest.amount = amount;
    offerRequest.email = email;
    offerRequest.message = message;
    offerRequest.phoneNumber = phoneNumber;
    offerRequest.name = name;

    return given()
            .body(offerRequest)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .post(PRODUCTS_END_POINT + "/" + productId + "/" + "offers");
  }
}
