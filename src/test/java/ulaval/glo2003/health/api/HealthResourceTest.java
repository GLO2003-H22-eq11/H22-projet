package ulaval.glo2003.health.api;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HealthResourceTest {

  private HealthResource healthResource = new HealthResource();

  @Test
  public void whenGetHealth_thenShouldReturnOkStatus() {
    int expectedStatus = 200;

    Response actualResponse = healthResource.getHealth();

    assertEquals(expectedStatus, actualResponse.getStatus());
  }

}