package ulaval.glo2003.endtoendsucces;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;


public class HealthEndToEndTest {

  @Test
  public void whenGetHealthCall_thenShouldReturnStatusOk() {

    RestAssured.given()
            .log().all()
            .get("/health")
            .then()
            .log().all()
            .statusCode(200);
  }

}
