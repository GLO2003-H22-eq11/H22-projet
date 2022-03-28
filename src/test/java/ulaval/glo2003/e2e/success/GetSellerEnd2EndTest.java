package ulaval.glo2003.e2e.success;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.seller.api.SellerResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.A_BIO;
import static ulaval.glo2003.e2e.End2EndConfig.A_SELLER_NAME;
import static ulaval.glo2003.e2e.End2EndConfig.OK_STATUS_CODE;
import static ulaval.glo2003.e2e.success.SellerEnd2EndTestUtils.createSellerWithProductAndGetSellerId;
import static ulaval.glo2003.e2e.success.SellerEnd2EndTestUtils.getSellerResponse;
import static ulaval.glo2003.e2e.success.SellerEnd2EndTestUtils.getSellerResponseBody;

public class GetSellerEnd2EndTest {

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
    ProductEnd2EndTestUtils.clearDatabase();
    SellerEnd2EndTestUtils.clearDatabase();
  }

  @Test
  public void givenASellerWithAProduct_whenGetSeller_thenShouldReturn200StatusCode() {
    String sellerId = createSellerWithProductAndGetSellerId();

    int statusCode = getSellerResponse(sellerId).statusCode();

    assertEquals(OK_STATUS_CODE, statusCode);
  }

  @Test
  public void givenASellerWithAProduct_whenGetSeller_thenShouldReturnTheRightBody() {
    String sellerId = createSellerWithProductAndGetSellerId();

    SellerResponse sellerResponse = getSellerResponseBody(sellerId);

    assertEquals(sellerResponse.id, sellerId);
    assertEquals(sellerResponse.bio, A_BIO);
    assertEquals(sellerResponse.name, A_SELLER_NAME);
    assertEquals(sellerResponse.products.size(), 1);
  }
}
