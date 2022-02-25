package ulaval.glo2003.endtoendechec;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetProductTest {

  @Test
  public void givenAProductRequestWithBadId_whenGetProduct_thenShouldReturnRightStatusCode() {
    RestAssured.given()
            .header("Content-Type", "application/json")
            .get("http://localhost:8080/products/" + "d3815eac")
            .then().assertThat().statusCode(400);

  }

  @Test
  public void givenAProductRequestWithNonExistingId_whenGetProduct_thenShouldReturnRightStatusCode() {
    RestAssured.given()
            .header("Content-Type", "application/json")
            .get("http://localhost:8080/products/" + "d3815eac-bf01-49bf-b570-3a8f376b8c15")
            .then().assertThat().statusCode(404);

  }

  @Test
  public void givenAProductRequestWithBadId_whenGetProduct_thenShouldReturnRightBody() {
    RestAssured.given()
            .header("Content-Type", "application/json")
            .get("http://localhost:8080/products/" + "d3815eac")
            .then().body("code", equalTo("INVALID_PARAMETER"))
            .body("description", equalTo("un paramètre (URL, header, JSON, etc.) est invalide (vide, négatif, trop long. etc.)"));

  }

  @Test
  public void givenAProductRequestWithNonExistingId_whenGetProduct_thenShouldReturnRightBody() {
    RestAssured.given()
            .header("Content-Type", "application/json")
            .get("http://localhost:8080/products/" + "d3815eac-bf01-49bf-b570-3a8f376b8c15")
            .then().body("code", equalTo("ITEM_NOT_FOUND"))
            .body("description", equalTo("un élément est introuvable (id invalide ou inexistant)"));

  }
}
