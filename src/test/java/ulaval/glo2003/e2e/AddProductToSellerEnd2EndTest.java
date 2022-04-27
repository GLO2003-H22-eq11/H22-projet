package ulaval.glo2003.e2e;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.ApplicationMain;
import ulaval.glo2003.product.api.request.ProductRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static spark.Spark.stop;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.ProductEnd2EndTestUtils.*;
import static ulaval.glo2003.e2e.SellerEnd2EndTestUtils.createSellerAndGetId;

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

  @AfterAll
  public static void clearDatabase() {
    ProductEnd2EndTestUtils.clearProductsDatabase();
    SellerEnd2EndTestUtils.clearSellersDatabase();
  }

  @Test
  public void givenASellerIdAndAProductRequest_whenCreateProduct_thenShouldReturn201StatusCode() {
    String sellerId = createSellerAndGetId();

    Response response = createProduct(sellerId);

    response.then().assertThat().statusCode(CREATED_STATUS_CODE);
  }

  @Test
  public void givenASellerIdAndAProductRequest_whenCreateProduct_thenShouldReturnAProductId() {
    String sellerId = createSellerAndGetId();

    String productId = createProductAndGetId(sellerId);

    assertTrue(productId.matches(UUID_REGEX));
  }

  @Test
  public void givenAProductRequestWithoutTitle_whenAddProduct_thenShouldReturn400StatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutTitle();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWithoutDescription_whenAddProduct_thenShouldReturn400StatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutDescription();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWithoutPrice_whenAddProduct_thenShouldReturn400StatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutPrice();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWitBadPrice_whenAddProduct_thenShouldReturn400StatusCode() {
    ProductRequest productRequest = givenAProductRequestWithBadPrice();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWithoutCategories_whenAddProduct_thenShouldReturn400StatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutCategories();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWithBadSellerId_whenAddProduct_thenShouldReturn404StatusCode() {
    ProductRequest productRequest = givenAProductRequest();

    postProductWithBody(productRequest, A_VALID_NON_EXISTING_UUID_FORMAT)
            .then().assertThat().statusCode(NOT_FOUND_STATUS_CODE);
  }

  @Test
  public void givenAProductRequestWithoutSellerId_whenAddProduct_thenShouldReturn400StatusCode() {
    ProductRequest productRequest = givenAProductRequest();

    postProductWithBody(productRequest, A_NON_VALID_UUID_FORMAT)
            .then().assertThat().statusCode(BAD_STATUS_CODE);

  }

  @Test
  public void givenAProductRequestWithBadSellerId_whenAddProduct_thenShouldReturnItemNotFoundBody() {
    ProductRequest productRequest = givenAProductRequest();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_ITEM_NOT_FOUND))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_ITEM_NOT_FOUND_DESCRIPTION));

  }

  @Test
  public void givenAProductRequestWithoutSellerId_whenAddProduct_thenShouldReturnInvalidParameterBody() {
    ProductRequest productRequest = givenAProductRequest();

    postProductWithBody(productRequest, A_NON_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));

  }

  @Test
  public void givenAProductRequestWithoutTitle_whenAddProduct_thenShouldReturnMissingParameterBody() {
    ProductRequest productRequest = givenAProductRequestWithoutTitle();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProductRequestWithoutDescription_whenAddProduct_thenShouldReturnMissingParameterBody() {
    ProductRequest productRequest = givenAProductRequestWithoutDescription();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProductRequestWithBadPrice_whenAddProduct_thenShouldReturnInvalidParameterBody() {
    ProductRequest productRequest = givenAProductRequestWithBadPrice();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(AN_INVALID_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProductRequestWithoutPrice_whenAddProduct_thenShouldReturnMissingParameterBody() {
    ProductRequest productRequest = givenAProductRequestWithoutPrice();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProductRequestWithoutCategories_whenAddProduct_thenShouldReturnMissingParameterBody() {
    ProductRequest productRequest = givenAProductRequestWithoutCategories();

    postProductWithBody(productRequest, A_VALID_UUID_FORMAT)
            .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }
}
