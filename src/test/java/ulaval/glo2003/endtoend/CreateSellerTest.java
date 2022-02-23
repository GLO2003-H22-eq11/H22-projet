package ulaval.glo2003.endtoend;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.seller.api.SellerRequest;

import static org.junit.jupiter.api.Assertions.*;

public class CreateSellerTest {

  private final String END_POINT = "/sellers";
  private final String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

  private final String A_SELLER_NAME = "Marin Beauchesne";
  private final String A_BIO = "I love chicken nuggets";
  private final String A_SELLER_DATE = "1999-07-08";
  private final int OK_STATUS_CODE = 201;

  @Test
  public void givenSellerInformation_whenCreationASeller_thenShouldReturnRightStatusCode() {

    SellerRequest sellerRequest = givenAValidSellerRequest();

    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.body(sellerRequest);

    request.post(this.END_POINT).then().statusCode(OK_STATUS_CODE);
  }

  @Test
  public void givenSellerInformation_whenCreationASeller_thenShouldTheSellerIdInLocation() {

    SellerRequest sellerRequest = givenAValidSellerRequest();

    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.body(sellerRequest);

    String URL_LOCATION  = request.post(this.END_POINT).getHeaders().getValue("Location");
    String sellerId = URL_LOCATION.split("sellers/")[1];

    assertTrue(URL_LOCATION.contains("http://localhost:8080/sellers/"));
    assertTrue(sellerId.matches(UUID_REGEX));
  }


  private SellerRequest givenAValidSellerRequest() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = A_SELLER_NAME;
    sellerRequest.bio = A_BIO;
    sellerRequest.birthDate = A_SELLER_DATE;
    return sellerRequest;
  }
}
