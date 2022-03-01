package ulaval.glo2003.e2e.success;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;

import static org.junit.jupiter.api.Assertions.*;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.CREATED_STATUS_CODE;
import static ulaval.glo2003.e2e.End2EndConfig.UUID_REGEX;
import static ulaval.glo2003.e2e.success.SellerEnd2EndTestUtils.createSeller;
import static ulaval.glo2003.e2e.success.SellerEnd2EndTestUtils.createSellerGetId;

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
  public void givenSellerInformation_whenCreateASeller_thenShouldReturn201StatusCode() {
    Response response = createSeller();

    response.then().assertThat().statusCode(CREATED_STATUS_CODE);
  }

  @Test
  public void givenSellerInformation_whenCreateASeller_thenShouldReturnSellerId() {
    String sellerId = createSellerGetId();

    assertTrue(sellerId.matches(UUID_REGEX));
  }
}
