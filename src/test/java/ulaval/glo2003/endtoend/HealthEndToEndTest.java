package ulaval.glo2003.endtoend;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class HealthEndToEndTest extends EndToEndConfig {

  @Test
  public void whenGetHealthCall_thenShouldReturnStatusOk() {

    given()
            .get(this.HEALTH_ENDPOINT)
            .then()
            .statusCode(this.GET_STATUS_CODE);
  }

}
