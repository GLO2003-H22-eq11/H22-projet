package ulaval.glo2003.e2e.echec;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ulaval.glo2003.seller.api.SellerRequest;

import static io.restassured.RestAssured.given;
import static ulaval.glo2003.e2e.End2EndConfig.APPLICATION_JSON;
import static ulaval.glo2003.e2e.End2EndConfig.A_BIO;
import static ulaval.glo2003.e2e.End2EndConfig.A_SELLER_DATE;
import static ulaval.glo2003.e2e.End2EndConfig.A_SELLER_NAME;
import static ulaval.glo2003.e2e.End2EndConfig.CONTENT_TYPE;
import static ulaval.glo2003.e2e.End2EndConfig.LOCATION;
import static ulaval.glo2003.e2e.End2EndConfig.SELLER_END_POINT;

public class SellerEnd2EndTestUtils {

  public static Response createSeller() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.bio = A_BIO;
    sellerRequest.birthDate = A_SELLER_DATE;

    RequestSpecification request = given().header(CONTENT_TYPE, APPLICATION_JSON).body(sellerRequest);
    return request.when().post(SELLER_END_POINT);
  }

  public SellerRequest givenASellerRequestWithoutName() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.bio = A_BIO;
    sellerRequest.birthDate = A_SELLER_DATE;
    return sellerRequest;
  }

  public SellerRequest givenASellerRequestWithoutBio() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.birthDate = A_SELLER_DATE;
    return sellerRequest;
  }

  public SellerRequest givenASellerRequestWithoutBirthDate() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.bio = A_BIO;
    return sellerRequest;
  }

  public SellerRequest givenASellerRequestWithBadBirthDate() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.bio = A_BIO;
    sellerRequest.birthDate = A_SELLER_DATE;
    return sellerRequest;
  }

  public static String createSellerGetId() {
    Response response = createSeller();
    String locationHeader = response.getHeader(LOCATION);
    return locationHeader.substring(locationHeader.lastIndexOf("/") + 1);
  }

}
