package ulaval.glo2003.endtoend;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.seller.api.SellerRequest;
import ulaval.glo2003.seller.api.SellerResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetSellerEndToEndTest extends EndToEndConfig {

  @Test
  public void givenASellerRequest_whenGetSeller_thenShouldReturnRightStatusCode() {
    String sellerId = addSellerAndGetSellerId();

    RestAssured.given()
            .header("Content-Type", "application/json")
            .get(this.URL_SELLERS_END_POINT + sellerId)
            .then().assertThat().statusCode(this.GET_STATUS_CODE);


  }

  @Test
  public void givenASellerRequest_whenGetSeller_thenShouldReturnTheRightBody() {
    String sellerId = addSellerAndGetSellerId();

    SellerResponse sellerResponse = RestAssured.given()
            .header("Content-Type", "application/json")
            .get(this.URL_SELLERS_END_POINT + sellerId)
            .getBody().as(SellerResponse.class);

    assertEquals(sellerResponse.id, sellerId);
    assertEquals(sellerResponse.bio, this.A_BIO);
    assertEquals(sellerResponse.name, this.A_SELLER_NAME);

  }

  public String addSellerAndGetSellerId() {
    SellerRequest sellerRequest = this.givenAValidSellerRequest();

    RequestSpecification request = RestAssured.given();
    request.header("Content-Type", "application/json");
    request.body(sellerRequest);

    String URL_LOCATION = request.post(this.SELLER_END_POINT).getHeaders()
            .getValue(this.LOCATION);
    String sellerId = URL_LOCATION.split("sellers/")[1];
    return sellerId;
  }
}
