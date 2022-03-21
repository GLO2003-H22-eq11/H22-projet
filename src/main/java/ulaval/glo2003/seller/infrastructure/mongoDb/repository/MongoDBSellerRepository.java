package ulaval.glo2003.seller.infrastructure.mongoDb.repository;

import dev.morphia.Datastore;
import ulaval.glo2003.seller.infrastructure.mongoDb.entity.SellerEntity;

public class MongoDBSellerRepository {

  public final Datastore datastore;

  public MongoDBSellerRepository(Datastore datastore) {
    this.datastore = datastore;
  }

  // example
  public void save(SellerEntity sellerEntity) {
    this.datastore.save(sellerEntity);
  }
}
