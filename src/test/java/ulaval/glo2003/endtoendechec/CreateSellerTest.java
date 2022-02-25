package ulaval.glo2003.endtoendechec;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.seller.api.SellerRequest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateSellerTest {

  @Test
  public void givenASellerNullName_whenCreateASeller_thenShouldReturnRightStatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithoutName();

    given()
            .header("Content-Type", "application/json")
            .body(sellerRequest)
            .post("http://localhost:8080/sellers")
            .then().statusCode(400);

  }

  @Test
  public void givenASellerNullBio_whenCreateASeller_thenShouldReturnRightStatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBio();

    given()
            .header("Content-Type", "application/json")
            .body(sellerRequest)
            .post("http://localhost:8080/sellers")
            .then().statusCode(400);
  }

  @Test
  public void givenASellerNullBirthDate_whenCreateASeller_thenShouldReturnRightStatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBirthDate();

    given()
            .header("Content-Type", "application/json")
            .body(sellerRequest)
            .post("http://localhost:8080/sellers")
            .then().statusCode(400);
  }

  @Test
  public void givenASellerBadBirthDate_whenCreateASeller_thenShouldReturnRightStatusCode() {
    SellerRequest sellerRequest = givenASellerRequestWithBadBirthDate();

    given()
            .header("Content-Type", "application/json")
            .body(sellerRequest)
            .post("http://localhost:8080/sellers")
            .then().statusCode(400);
  }

  @Test
  public void givenASellerNullName_whenCreateASeller_thenShouldReturnRightBody() {
    SellerRequest sellerRequest = givenASellerRequestWithoutName();

    given()
            .header("Content-Type", "application/json")
            .body(sellerRequest)
            .post("http://localhost:8080/sellers")
            .then().body("code", equalTo("MISSING_PARAMETER"))
            .body("description", equalTo("un paramètre (URL, header, JSON, etc.) est manquant"));

  }

  @Test
  public void givenASellerNullBio_whenCreateASeller_thenShouldReturnRightBody() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBio();

    given()
            .header("Content-Type", "application/json")
            .body(sellerRequest)
            .post("http://localhost:8080/sellers")
            .then().body("code", equalTo("MISSING_PARAMETER"))
            .body("description", equalTo("un paramètre (URL, header, JSON, etc.) est manquant"));

  }

  @Test
  public void givenASellerNullBirthDate_whenCreateASeller_thenShouldReturnRightBody() {
    SellerRequest sellerRequest = givenASellerRequestWithoutBirthDate();

    given()
            .header("Content-Type", "application/json")
            .body(sellerRequest)
            .post("http://localhost:8080/sellers")
            .then().body("code", equalTo("MISSING_PARAMETER"))
            .body("description", equalTo("un paramètre (URL, header, JSON, etc.) est manquant"));

  }

  @Test
  public void givenASellerBadBirthDate_whenCreateASeller_thenShouldReturnRightBody() {
    SellerRequest sellerRequest = givenASellerRequestWithBadBirthDate();

    given()
            .header("Content-Type", "application/json")
            .body(sellerRequest)
            .post("http://localhost:8080/sellers")
            .then().body("code", equalTo("INVALID_PARAMETER"))
            .body("description", equalTo("un paramètre (URL, header, JSON, etc.) est invalide (vide, négatif, trop long. etc.)"));

  }

  public SellerRequest givenASellerRequestWithoutName() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.bio = "bio";
    sellerRequest.birthDate = "2000-10-10";
    return sellerRequest;
  }

  public SellerRequest givenASellerRequestWithoutBio() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = "name";
    sellerRequest.birthDate = "2000-10-10";
    return sellerRequest;
  }

  public SellerRequest givenASellerRequestWithoutBirthDate() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = "name";
    sellerRequest.bio = "bio";
    return sellerRequest;
  }

  public SellerRequest givenASellerRequestWithBadBirthDate() {
    SellerRequest sellerRequest = new SellerRequest();
    sellerRequest.name = "name";
    sellerRequest.bio = "bio";
    sellerRequest.birthDate = "2006-10-10";
    return sellerRequest;
  }
}
