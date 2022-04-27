package ulaval.glo2003.e2e;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.seller.api.request.SellerRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.SellerEnd2EndTestUtils.*;

public class CreateSellerEnd2EndTest {

  @BeforeAll
  public static void startServer() {
    try {
      ApplicationMain.main(new String[0]);

    } catch (Exception ignored) {
    }
  }

  @AfterAll
  public static void stopServer() {
    stop();
  }

  @AfterAll
  public static void clearDatabase() {
    ProductEnd2EndTestUtils.clearProductsDatabase();
    SellerEnd2EndTestUtils.clearSellersDatabase();
  }

  @Test
  public void givenSellerInformation_whenCreateASeller_thenShouldReturn201StatusCode() {
    Response response = createSeller();

    response.then().assertThat().statusCode(CREATED_STATUS_CODE);
  }

  @Test
  public void givenSellerInformation_whenCreateASeller_thenShouldReturnSellerId() {
    String sellerId = createSellerAndGetId();

    assertTrue(sellerId.matches(UUID_REGEX));
  }

  @Test
  public void givenASellerNullName_whenCreateASeller_thenShouldReturn400StatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithoutName();

    postSellerWithSellerBody(sellerRequest)
            .then().statusCode(BAD_STATUS_CODE);

  }

  @Test
  public void givenASellerNullBio_whenCreateASeller_thenShouldReturn400StatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBio();

    postSellerWithSellerBody(sellerRequest)
            .then().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenASellerNullBirthDate_whenCreateASeller_thenShouldReturn400StatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBirthDate();

    postSellerWithSellerBody(sellerRequest)
            .then().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenASellerBadBirthDate_whenCreateASeller_thenShouldReturn400StatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithBadBirthDate();

    postSellerWithSellerBody(sellerRequest)
            .then().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenASellerNullName_whenCreateASeller_thenShouldReturnMissingParameterBody() {
    SellerRequest sellerRequest = givenASellerRequestWithoutName();

    postSellerWithSellerBody(sellerRequest)
            .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));

  }

  @Test
  public void givenASellerNullBio_whenCreateASeller_thenShouldReturnMissingParameterBody() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBio();

    postSellerWithSellerBody(sellerRequest)
            .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));

  }

  @Test
  public void givenASellerNullBirthDate_whenCreateASeller_thenShouldReturnMissingParameterBody() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBirthDate();

    postSellerWithSellerBody(sellerRequest)
            .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));

  }

  @Test
  public void givenASellerBadBirthDate_whenCreateASeller_thenShouldReturnInvalidParameterBody() {
    SellerRequest sellerRequest = givenASellerRequestWithBadBirthDate();

    postSellerWithSellerBody(sellerRequest)
            .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));

  }
}
