package ulaval.glo2003.e2e.failure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.seller.api.SellerRequest;

import static org.hamcrest.Matchers.equalTo;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.failure.SellerEnd2EndTestUtils.*;

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

  @Test
  public void givenASellerNullName_whenCreateASeller_thenShouldReturnRightStatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithoutName();

    postSellerWithSellerBody(sellerRequest)
      .then().statusCode(BAD_STATUS_CODE);

  }

  @Test
  public void givenASellerNullBio_whenCreateASeller_thenShouldReturnRightStatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBio();

    postSellerWithSellerBody(sellerRequest)
      .then().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenASellerNullBirthDate_whenCreateASeller_thenShouldReturnRightStatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBirthDate();

    postSellerWithSellerBody(sellerRequest)
      .then().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenASellerBadBirthDate_whenCreateASeller_thenShouldReturnRightStatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithBadBirthDate();

    postSellerWithSellerBody(sellerRequest)
      .then().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenASellerNullName_whenCreateASeller_thenShouldReturnRightBody() {
    SellerRequest sellerRequest = givenASellerRequestWithoutName();

    postSellerWithSellerBody(sellerRequest)
      .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
      .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));

  }

  @Test
  public void givenASellerNullBio_whenCreateASeller_thenShouldReturnRightBody() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBio();

    postSellerWithSellerBody(sellerRequest)
      .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
      .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));

  }

  @Test
  public void givenASellerNullBirthDate_whenCreateASeller_thenShouldReturnRightBody() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBirthDate();

    postSellerWithSellerBody(sellerRequest)
      .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
      .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));

  }

  @Test
  public void givenASellerBadBirthDate_whenCreateASeller_thenShouldReturnRightBody() {
    SellerRequest sellerRequest = givenASellerRequestWithBadBirthDate();

    postSellerWithSellerBody(sellerRequest)
      .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
      .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));

  }
}
