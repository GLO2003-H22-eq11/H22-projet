package ulaval.glo2003.e2e.failure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.failure.ProductEnd2EndTestUtils.getProductWithProductId;

public class GetProductEnd2EndTest {

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
  public void givenAProductRequestWithBadId_whenGetProduct_thenShouldReturnRightStatusCode() {
    getProductWithProductId(A_NON_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);

  }

  @Test
  public void givenAProductRequestWithNonExistingId_whenGetProduct_thenShouldReturnRightStatusCode() {
    getProductWithProductId(A_VALID_NON_EXISTING_UUID_FORMAT)
            .then().assertThat().statusCode(NOT_FOUND_STATUS_CODE);

  }

  @Test
  public void givenAProductRequestWithBadId_whenGetProduct_thenShouldReturnRightBody() {
    getProductWithProductId(A_NON_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));

  }

  @Test
  public void givenAProductRequestWithNonExistingId_whenGetProduct_thenShouldReturnRightBody() {
    getProductWithProductId(A_VALID_NON_EXISTING_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_ITEM_NOT_FOUND))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_ITEM_NOT_FOUND_DESCRIPTION));

  }
}
