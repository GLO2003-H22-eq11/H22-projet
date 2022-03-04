package ulaval.glo2003.e2e.failure;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ulaval.glo2003.seller.api.SellerRequest;

import static io.restassured.RestAssured.given;
import static ulaval.glo2003.e2e.End2EndConfig.*;

public class SellerEnd2EndTestUtils {

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
