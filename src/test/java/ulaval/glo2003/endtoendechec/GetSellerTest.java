package ulaval.glo2003.endtoendechec;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetSellerTest {

  @Test
  public void givenANonExistingSellerId_whenGetSeller_thenShouldReturnRightStatusCode() {
    RestAssured.given()
            .header("Content-Type", "application/json")
            .get("http://localhost:8080/sellers/" + "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5")
            .then().assertThat().statusCode(404);

  }
  @Test
  public void givenABadSellerId_whenGetSeller_thenShouldReturnRightStatusCode() {
    RestAssured.given()
            .header("Content-Type", "application/json")
            .get("http://localhost:8080/sellers/" + "aaaaaa")
            .then().assertThat().statusCode(400);

  }

  @Test
  public void givenABadSellerId_whenGetSeller_thenShouldReturnRightBody() {
    RestAssured.given()
            .header("Content-Type", "application/json")
            .get("http://localhost:8080/sellers/" + "0ce7f24b-e80e-4513-a7bc-78ae2a71c3a5")
            .then().body("code", equalTo("ITEM_NOT_FOUND"))
            .body("description", equalTo("un élément est introuvable (id invalide ou inexistant)"));

  }

  @Test
  public void givenANonExistingSellerId_whenGetSeller_thenShouldReturnRightBody() {
    RestAssured.given()
            .header("Content-Type", "application/json")
            .get("http://localhost:8080/sellers/" + "aaaaaa")
            .then().body("code", equalTo("INVALID_PARAMETER"))
            .body("description", equalTo("un paramètre (URL, header, JSON, etc.) est invalide (vide, négatif, trop long. etc.)"));

  }
}
