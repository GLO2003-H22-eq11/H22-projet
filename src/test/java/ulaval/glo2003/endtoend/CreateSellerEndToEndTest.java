package ulaval.glo2003.endtoend;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.seller.api.SellerRequest;

import static org.junit.jupiter.api.Assertions.*;

public class CreateSellerEndToEndTest extends EndToEndConfig  {

  @Test
  public void givenSellerInformation_whenCreationASeller_thenShouldReturnRightStatusCode() {

    SellerRequest sellerRequest = this.givenAValidSellerRequest();

    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.body(sellerRequest);

    request.post(this.SELLER_END_POINT).then().statusCode(this.NO_CONTENT_STATUS_CODE);
  }

  @Test
  public void givenSellerInformation_whenCreationASeller_thenShouldTheSellerIdInLocation() {

    SellerRequest sellerRequest = this.givenAValidSellerRequest();

    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.body(sellerRequest);

    String URL_LOCATION  = request.post(this.SELLER_END_POINT).getHeaders()
            .getValue(this.LOCATION);
    String sellerId = URL_LOCATION.split("sellers/")[1];

    assertTrue(URL_LOCATION.contains(this.URL_SELLERS_END_POINT));
    assertTrue(sellerId.matches(this.UUID_REGEX));
  }

}
