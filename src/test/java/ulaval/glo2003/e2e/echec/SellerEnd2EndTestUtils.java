package ulaval.glo2003.e2e.echec;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ulaval.glo2003.seller.api.SellerRequest;

import static io.restassured.RestAssured.given;
import static ulaval.glo2003.e2e.End2EndConfig.*;

public class SellerEnd2EndTestUtils {

  public static Response createSeller() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.bio = A_BIO;
    sellerRequest.birthDate = A_SELLER_DATE;

    RequestSpecification request = given().header(CONTENT_TYPE, APPLICATION_JSON).body(sellerRequest);
    return request.when().post(SELLER_END_POINT);
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

  public static String createSellerGetId() {
    Response response = createSeller();
    String locationHeader = response.getHeader(LOCATION);
    return locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
  }

}
