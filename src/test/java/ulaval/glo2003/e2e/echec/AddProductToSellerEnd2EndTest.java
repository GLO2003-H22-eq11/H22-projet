package ulaval.glo2003.e2e.echec;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.api.ProductRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static ulaval.glo2003.e2e.End2EndConfig.*;
import static ulaval.glo2003.e2e.echec.ProductEnd2EndTestUtils.*;

public class AddProductToSellerEnd2EndTest {

  @Test
  public void givenAProductRequestWithoutTitle_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutTitle();

    given()
      .body(productRequest)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(X_SELLER_ID_HEADERS_PARAMS, A_VALID_UUID_FORMAT)
      .post(URL_PRODUCTS_END_POINT)
      .then().assertThat().statusCode(400);
  }

  @Test
  public void givenAProductRequestWithoutDescription_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutDescription();

    given()
      .body(productRequest)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(X_SELLER_ID_HEADERS_PARAMS, A_VALID_UUID_FORMAT)
      .post(URL_PRODUCTS_END_POINT)
      .then().assertThat().statusCode(400);
  }

//  @Test
//  public void givenAProductRequestWithoutPrice_whenAddProduct_thenShouldReturnTheRightStatusCode(){
//    ProductRequest productRequest = givenAProductRequestWithoutPrice();
//  }

  @Test
  public void givenAProductRequestWithoutCategories_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutCategories();

    given()
      .body(productRequest)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(X_SELLER_ID_HEADERS_PARAMS, A_VALID_UUID_FORMAT)
      .post(URL_PRODUCTS_END_POINT)
      .then().assertThat().statusCode(400);
  }

  @Test
  public void givenAProductRequestWithBadSellerId_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequest();

    given()
      .body(productRequest)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(X_SELLER_ID_HEADERS_PARAMS, A_VALID_UUID_FORMAT)
      .post(URL_PRODUCTS_END_POINT)
      .then().assertThat().statusCode(404);
  }

//  @Test
//  public void givenAProductRequestWithoutSellerId_whenAddProduct_thenShouldReturnTheRightStatusCode() {
//    ProductRequest productRequest = givenAProductRequest();
//  }

  @Test
  public void givenAProductRequestWithBadSellerId_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequest();

    given()
      .body(productRequest)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(X_SELLER_ID_HEADERS_PARAMS, A_VALID_UUID_FORMAT)
      .post(URL_PRODUCTS_END_POINT)
      .then().body(AN_ERROR, equalTo(AN_ITEM_NOT_FOUND))
      .body(AN_ERROR_DESCRIPTION, equalTo(AN_ITEM_NOT_FOUND_DESCRIPTION));

  }

  @Test
  public void givenAProductRequestWithoutTitle_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithoutTitle();

    given()
      .body(productRequest)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(X_SELLER_ID_HEADERS_PARAMS, A_VALID_UUID_FORMAT)
      .post(URL_PRODUCTS_END_POINT)
      .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
      .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProductRequestWithoutDescription_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithoutDescription();

    given()
      .body(productRequest)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(X_SELLER_ID_HEADERS_PARAMS, A_VALID_UUID_FORMAT)
      .post(URL_PRODUCTS_END_POINT)
      .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
      .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProductRequestWithBadPrice_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithoutPrice();
    System.out.println(productRequest.suggestedPrice);
    given()
            .body(productRequest)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(X_SELLER_ID_HEADERS_PARAMS, A_VALID_UUID_FORMAT)
            .post(URL_PRODUCTS_END_POINT)
            .then().body(AN_ERROR, equalTo(AN_INVALID_PARAMETER))
            .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }

  @Test
  public void givenAProductRequestWithoutCategories_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithoutCategories();

    given()
      .body(productRequest)
      .header(CONTENT_TYPE, APPLICATION_JSON)
      .header(X_SELLER_ID_HEADERS_PARAMS, A_VALID_UUID_FORMAT)
      .post(URL_PRODUCTS_END_POINT)
      .then().body(AN_ERROR, equalTo(A_MISSING_PARAMETER))
      .body(AN_ERROR_DESCRIPTION, equalTo(A_MISSING_PARAMETER_DESCRIPTION));
  }
}
