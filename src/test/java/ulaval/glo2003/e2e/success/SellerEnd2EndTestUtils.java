package ulaval.glo2003.e2e.success;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ulaval.glo2003.seller.api.SellerRequest;
import ulaval.glo2003.seller.api.SellerResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static ulaval.glo2003.e2e.End2EndConfig.APPLICATION_JSON;
import static ulaval.glo2003.e2e.End2EndConfig.A_BIO;
import static ulaval.glo2003.e2e.End2EndConfig.A_SELLER_DATE;
import static ulaval.glo2003.e2e.End2EndConfig.A_SELLER_NAME;
import static ulaval.glo2003.e2e.End2EndConfig.CONTENT_TYPE;
import static ulaval.glo2003.e2e.End2EndConfig.LOCATION;
import static ulaval.glo2003.e2e.End2EndConfig.SELLER_END_POINT;
import static ulaval.glo2003.e2e.success.ProductEnd2EndTestUtils.createProduct;

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

  public static SellerResponse getSellerResponseBody(String sellerId) {
    return getSellerResponse(sellerId).getBody().as(SellerResponse.class);
  }

  public static void clearSellersDatabase() {
    given().delete(SELLER_END_POINT + "/clear");
  }
}
