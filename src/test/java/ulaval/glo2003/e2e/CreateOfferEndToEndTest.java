package ulaval.glo2003.e2e;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.End2EndConfig.AN_INVALID_PARAMETER_DESCRIPTION;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.createOffer;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.createOfferWithInvalidParams;
import static ulaval.glo2003.e2e.SellerEnd2EndTestUtils.createSellerGetId;

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
  public void givenAnOfferOnAnExistingProduct_whenCreateOffer_thenShouldReturn200StatusCode(){
    String sellerId = createSellerGetId();

    Response response = createOffer(sellerId);

    response.then().assertThat().statusCode(OK_STATUS_CODE);
  }

  @Test
  public void givenAnOfferForANonExistingProduct_whenCreateOffer_thenShouldReturn404StatusCode() {
    Response response = createOfferWithInvalidParams(A_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_VALID_EMAIL,
            A_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            A_VALID_NAME);
    assertEquals(response.getStatusCode(), NOT_FOUND_STATUS_CODE);
  }

  @Test
  public void givenAnOfferForANonValidProduct_whenCreateOffer_thenShouldReturn400StatusCode() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_VALID_EMAIL,
            A_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            A_VALID_NAME);
    assertEquals(response.getStatusCode(), BAD_STATUS_CODE);
  }

  @Test
  public void givenAnOfferWithAnInvalidFieldForAProduct_whenCreateOffer_thenShouldReturn400StatusCode() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_NON_VALID_EMAIL,
            A_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            A_VALID_NAME);
    assertEquals(response.getStatusCode(), BAD_STATUS_CODE);
  }

  @Test
  public void givenAnOfferWithAnEmptyFieldForAProduct_whenCreateOffer_thenShouldReturn400StatusCode() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            null,
            A_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            A_VALID_NAME);
    assertEquals(response.getStatusCode(), BAD_STATUS_CODE);
  }

  @Test
  public void givenAnOfferForANonExistingProduct_whenCreateOffer_thenShouldReturnItemNotFoundBody() {
    Response response = createOfferWithInvalidParams(A_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_VALID_EMAIL,
            A_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            A_VALID_NAME);
    response.then().body(AN_ERROR, equalTo(AN_ITEM_NOT_FOUND))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_ITEM_NOT_FOUND_DESCRIPTION));
  }

  @Test
  public void givenAnOfferForANonValidProduct_whenCreateOffer_thenShouldReturnInvalidParameterBody() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_VALID_EMAIL,
            A_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            A_VALID_NAME);
    response.then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAnOfferWithAnInvalidEmailForAProduct_whenCreateOffer_thenShouldReturnInvalidParameterBody() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_NON_VALID_EMAIL,
            A_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            A_VALID_NAME);
    response.then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAnOfferWithAnEmptyEmailForAProduct_whenCreateOffer_thenShouldReturnMissingParameterBody() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            null,
            A_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            A_VALID_NAME);
    response.then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAnOfferWithAnInvalidMessageForAProduct_whenCreateOffer_thenShouldReturnInvalidParameterBody() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_VALID_EMAIL,
            A_NON_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            A_VALID_NAME);
    response.then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAnOfferWithAnEmptyMessageForAProduct_whenCreateOffer_thenShouldReturnMissingParameterBody() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_VALID_EMAIL,
            null,
            A_VALID_PHONE_NUMBER,
            A_VALID_NAME);
    response.then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAnOfferWithAnInvalidPhoneForAProduct_whenCreateOffer_thenShouldReturnInvalidParameterBody() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_VALID_EMAIL,
            A_VALID_MESSAGE,
            A_NON_VALID_PHONE_NUMBER,
            A_VALID_NAME);
    response.then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAnOfferWithAnEmptyPhoneForAProduct_whenCreateOffer_thenShouldReturnMissingParameterBody() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_NON_VALID_EMAIL,
            A_VALID_MESSAGE,
            null,
            A_VALID_NAME);
    response.then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAnOfferWithAnInvalidNameForAProduct_whenCreateOffer_thenShouldReturnInvalidParameterBody() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_VALID_EMAIL,
            A_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            A_NON_VALID_NAME);
    response.then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAnOfferWithAnEmptyNameForAProduct_whenCreateOffer_thenShouldReturnMissingParameterBody() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_VALID_AMOUNT,
            A_NON_VALID_EMAIL,
            A_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            null);
    response.then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAnOfferWithAnInvalidAmountForAProduct_whenCreateOffer_thenShouldReturnInvalidParameterBody() {
    Response response = createOfferWithInvalidParams(A_NON_VALID_UUID_FORMAT,
            A_NON_VALID_AMOUNT,
            A_NON_VALID_EMAIL,
            A_VALID_MESSAGE,
            A_VALID_PHONE_NUMBER,
            A_NON_VALID_NAME);
    response.then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }
}
