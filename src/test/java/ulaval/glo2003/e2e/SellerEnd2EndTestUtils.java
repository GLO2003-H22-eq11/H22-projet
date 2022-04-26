package ulaval.glo2003.e2e;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ulaval.glo2003.seller.api.request.SellerRequest;
import ulaval.glo2003.seller.api.response.SellerResponse;
import ulaval.glo2003.seller.api.response.SellerWithProductsResponse;

import static io.restassured.RestAssured.*;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.createProduct;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.createProductAndGetId;

public class SellerEnd2EndTestUtils {

  public static Response createSeller() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.bio = A_BIO;
    sellerRequest.birthDate = A_SELLER_DATE;

    RequestSpecification request = given().header(CONTENT_TYPE, APPLICATION_JSON).body(sellerRequest);
    return request.when().post(SELLER_END_POINT);
  }

  public static String createSellerWithProductAndGetSellerId() {
    String sellerId = createSellerGetId();
    createProduct(sellerId);

    return sellerId;
  }

  public static String createSellerWithProductAndGetProductId() {
    String sellerId = createSellerGetId();
    String productId = createProductAndGetId(sellerId);

    return productId;
  }

  public static Response createSellerWithProduct() {
    String sellerId = createSellerGetId();
    return createProduct(sellerId);
  }

  public static String createSellerGetId() {
    Response response = createSeller();
    String locationHeader = response.getHeader(LOCATION);
    return locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
  }

  public static Response getSellerResponse(String sellerId) {
    return when().get(SELLER_END_POINT + "/" + sellerId);
  }

  public static Response getCurrentSellerResponse(String sellerId){

    return given().urlEncodingEnabled(false)
            .headers(X_SELLER_ID_HEADERS_PARAMS, sellerId)
            .get(SELLER_END_POINT + "/@me");
  }

  public static SellerWithProductsResponse getCurrentSellerBody(String sellerId){
    return getCurrentSellerResponse(sellerId).getBody().as(SellerWithProductsResponse.class);
  }

  public static SellerResponse getSellerResponseBody(String sellerId) {
    return getSellerResponse(sellerId).getBody().as(SellerResponse.class);
  }

  public static void clearSellersDatabase() {
    given().delete(SELLER_END_POINT + "/clear");
  }

  public static SellerRequest givenASellerRequestWithoutName() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.bio = A_BIO;
    sellerRequest.birthDate = A_SELLER_DATE;
    return sellerRequest;
  }

  public static SellerRequest givenASellerRequestWithoutBio() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.birthDate = A_SELLER_DATE;
    return sellerRequest;
  }

  public static SellerRequest givenASellerRequestWithoutBirthDate() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.bio = A_BIO;
    return sellerRequest;
  }

  public static SellerRequest givenASellerRequestWithBadBirthDate() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.bio = A_BIO;
    sellerRequest.birthDate = A_BAD_SELLER_DATE;
    return sellerRequest;
  }

  public static Response getSellerWithSellerId(String sellerId) {
    return given()
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .get(URL_SELLERS_END_POINT + "/" + sellerId);
  }


  public static Response postSellerWithSellerBody(SellerRequest sellerRequest) {
    return given()
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .body(sellerRequest)
            .post(URL_SELLERS_END_POINT);
  }
}
