package ulaval.glo2003.endtoend;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EndToEndTest {

  @BeforeEach()
  public void setUp() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 8081;
  }

  @Test
  public void healthCall() {

    RestAssured.given()
            .log().all()
            .get("/health")
            .then()
            .log().all()
            .statusCode(200);
  }

}
