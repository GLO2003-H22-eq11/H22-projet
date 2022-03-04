package ulaval.glo2003.e2e.failure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import ulaval.glo2003.ApplicationMain;

import static spark.Spark.stop;

public class GetProductWithFiltersEnd2EndTest {

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
}
