package ulaval.glo2003.health.infrastructure;

import dev.morphia.Datastore;
import ulaval.glo2003.health.infrastructure.exceptions.DatabaseConnectionException;

public class DatabaseConnection {
  private final Datastore datastore;

  public DatabaseConnection(Datastore datastore) {
    this.datastore = datastore;
  }

  public void isAlive() throws DatabaseConnectionException {
    try {
      this.datastore.getSession();
    } catch (Exception ignored) {
      throw new DatabaseConnectionException();
    }
  }
}
