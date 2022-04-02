package ulaval.glo2003.e2e.failure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.e2e.success.ProductEnd2EndTestUtils;
import ulaval.glo2003.e2e.success.SellerEnd2EndTestUtils;

import static spark.Spark.stop;

public class CreateOfferEndToEndTest {

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
  public void givenAnNonExistingProductAndAnOfferRequest_whenCreateOffer_thenShouldReturn404StatusCode(){

  }

  @Test
  public void givenAProductAndAnOfferRequestWithAnInvalidField_whenCreateOffer_thenShouldReturn400StatusCode(){

  }

  @Test
  public void givenAProductAndAnOfferRequestWithAnEmptyField_whenCreateOffer_thenShouldReturn400StatusCode(){

  }
}
