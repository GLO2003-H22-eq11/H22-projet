package ulaval.glo2003.e2e.success;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.OK_STATUS_CODE;
import static ulaval.glo2003.e2e.success.SellerEnd2EndTestUtils.*;

public class GetCurrentSellerEndToEndTest {

//  @BeforeAll
//  public static void startServer() {
//    try {
//      ApplicationMain.main(new String[0]);
//    } catch (Exception ignored) {
//    }
//  }

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
  public void givenASellerId_whenGetCurrentSellerId_thenShouldReturn200StatusCode() {
    String sellerId = createSellerGetId();

    Response response = getCurrentSellerResponse(sellerId);

    response.then().assertThat().statusCode(OK_STATUS_CODE);
  }
}
