package ulaval.glo2003.endtoendechec;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.api.ProductRequest;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class AddProductToSeller {

  @Test
  public void givenAProductRequestWithoutTitle_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutTitle();

    RestAssured.given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header("X-Seller-Id", "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5")
            .post("http://localhost:8080/products")
            .then().assertThat().statusCode(400);
  }

  @Test
  public void givenAProductRequestWithoutDescription_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutDescription();

    RestAssured.given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header("X-Seller-Id", "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5")
            .post("http://localhost:8080/products")
            .then().assertThat().statusCode(400);
  }

//  @Test
//  public void givenAProductRequestWithoutPrice_whenAddProduct_thenShouldReturnTheRightStatusCode(){
//    ProductRequest productRequest = givenAProductRequestWithoutPrice();
//  }

  @Test
  public void givenAProductRequestWithoutCategories_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequestWithoutCategories();

    RestAssured.given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header("X-Seller-Id", "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5")
            .post("http://localhost:8080/products")
            .then().assertThat().statusCode(400);
  }

  @Test
  public void givenAProductRequestWithBadSellerId_whenAddProduct_thenShouldReturnTheRightStatusCode() {
    ProductRequest productRequest = givenAProductRequest();

    RestAssured.given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header("X-Seller-Id", "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5")
            .post("http://localhost:8080/products")
            .then().assertThat().statusCode(404);
  }

//  @Test
//  public void givenAProductRequestWithoutSellerId_whenAddProduct_thenShouldReturnTheRightStatusCode() {
//    ProductRequest productRequest = givenAProductRequest();
//  }

  @Test
  public void givenAProductRequestWithBadSellerId_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequest();

    RestAssured.given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header("X-Seller-Id", "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5")
            .post("http://localhost:8080/products")
            .then().body("code", equalTo("ITEM_NOT_FOUND"))
            .body("description", equalTo("un élément est introuvable (id invalide ou inexistant)"));

  }

  @Test
  public void givenAProductRequestWithoutTitle_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithoutTitle();

    RestAssured.given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header("X-Seller-Id", "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5")
            .post("http://localhost:8080/products")
            .then().body("code", equalTo("MISSING_PARAMETER"))
            .body("description", equalTo("un paramètre (URL, header, JSON, etc.) est manquant"));
  }

  @Test
  public void givenAProductRequestWithoutDescription_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithoutDescription();

    RestAssured.given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header("X-Seller-Id", "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5")
            .post("http://localhost:8080/products")
            .then().body("code", equalTo("MISSING_PARAMETER"))
            .body("description", equalTo("un paramètre (URL, header, JSON, etc.) est manquant"));
  }

//  @Test
//  public void givenAProductRequestWithoutPrice_whenAddProduct_thenShouldReturnTheRightBody() {
//    ProductRequest productRequest = givenAProductRequestWithoutPrice();
//
//    RestAssured.given()
//            .body(productRequest)
//            .header("Content-Type", "application/json")
//            .header("X-Seller-Id", "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5")
//            .post("http://localhost:8080/products")
//            .then().body("code", equalTo("MISSING_PARAMETER"))
//            .body("description", equalTo("un paramètre (URL, header, JSON, etc.) est manquant"));
//  }

  @Test
  public void givenAProductRequestWithoutCategories_whenAddProduct_thenShouldReturnTheRightBody() {
    ProductRequest productRequest = givenAProductRequestWithoutCategories();

    RestAssured.given()
            .body(productRequest)
            .header("Content-Type", "application/json")
            .header("X-Seller-Id", "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5")
            .post("http://localhost:8080/products")
            .then().body("code", equalTo("MISSING_PARAMETER"))
            .body("description", equalTo("un paramètre (URL, header, JSON, etc.) est manquant"));
  }

  public ProductRequest givenAProductRequestWithoutCategories() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = "A description";
    productRequest.title = "A title";
    productRequest.suggestedPrice = 2;

    return productRequest;
  }

  public ProductRequest givenAProductRequestWithoutDescription() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.title = "A title";
    productRequest.suggestedPrice = 2;
    productRequest.categories = List.of("A", "B", "C");

    return productRequest;
  }

  public ProductRequest givenAProductRequestWithoutTitle() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = "A description";
    productRequest.suggestedPrice = 2;
    productRequest.categories = List.of("A", "B", "C");

    return productRequest;
  }

//  public ProductRequest givenAProductRequestWithoutPrice() {
//    ProductRequest productRequest = new ProductRequest();
//    productRequest.description = "A description";
//    productRequest.title = "A title";
//    productRequest.categories = List.of("A", "B", "C");
//
//    return productRequest;
//  }

  public ProductRequest givenAProductRequest() {
    ProductRequest productRequest = new ProductRequest();
    productRequest.description = "A description";
    productRequest.title = "A title";
    productRequest.suggestedPrice = 2;
    productRequest.categories = List.of("A", "B", "C");

    return productRequest;
  }
}

