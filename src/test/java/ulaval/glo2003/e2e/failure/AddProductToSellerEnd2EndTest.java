package ulaval.glo2003.e2e.failure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.product.api.ProductRequest;

import static org.hamcrest.Matchers.equalTo;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.failure.ProductEnd2EndTestUtils.*;

public class AddProductToSellerEnd2EndTest {

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
  public void givenAProductRequestWithoutTitle_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutTitle();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWithoutDescription_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutDescription();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWithoutPrice_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutPrice();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWitBadPrice_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequestWithBadPrice();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWithoutCategories_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutCategories();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWithBadSellerId_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequest();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(NOT_FOUND_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWithoutSellerId_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequest();

    postProductWithBody(productRequest, A_NON_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);

  }

  @Test
  public void givenAProductRequestWithBadSellerId_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequest();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_ITEM_NOT_FOUND))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_ITEM_NOT_FOUND_DESCRIPTION));

  }

  @Test
  public void givenAProductRequestWithoutSellerId_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequest();

    postProductWithBody(productRequest, A_NON_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));

  }

  @Test
  public void givenAProductRequestWithoutTitle_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithoutTitle();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProductRequestWithoutDescription_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithoutDescription();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProductRequestWithBadPrice_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithBadPrice();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProductRequestWithoutPrice_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithoutPrice();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProductRequestWithoutCategories_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithoutCategories();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }
}
