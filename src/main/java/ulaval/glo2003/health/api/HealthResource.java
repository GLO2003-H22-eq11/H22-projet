package ulaval.glo2003.health.api;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.health.infrastructure.DatabaseConnection;
import ulaval.glo2003.health.infrastructure.exceptions.DatabaseConnectionException;

@Path("/health")
@Produces(MediaType.APPLICATION_JSON)
public class HealthResource {
  private final DatabaseConnection databaseConnection;

  public HealthResource(DatabaseConnection databaseConnection) {
    this.databaseConnection = databaseConnection;
  }

  @GET
  public Response getHealth() {
    try {
      this.databaseConnection.isAlive();
      return Response.ok().entity(new HealthResponse(true, true)).build();
    } catch (DatabaseConnectionException e) {
      return Response.serverError().entity(new HealthResponse(true, false)).build();
    } catch (Exception e) {
      return Response.serverError().entity(new HealthResponse(null, null)).build();
    }
  }
}
