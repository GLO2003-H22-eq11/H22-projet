package ulaval.glo2003.product.infrastructure.mongodb.repository;

import dev.morphia.Datastore;
import ulaval.glo2003.product.infrastructure.mongodb.entity.ProductEntity;

public class MongoDBProductRepository {

  private final Datastore datastore;

  public MongoDBProductRepository(Datastore datastore) {
    this.datastore = datastore;
  }

  public void save(ProductEntity productEntity) {
    this.datastore.save(productEntity);
  }
}
