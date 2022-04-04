package ulaval.glo2003.health.api;

public class HealthResponse {
  public Boolean api;
  public Boolean db;

  public HealthResponse(Boolean api, Boolean db) {
    this.api = api;
    this.db = db;
  }
}
