package ulaval.glo2003.product.infrastructure.mongodb.repository;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.OfferRepository;
import ulaval.glo2003.product.infrastructure.mongodb.MongoDbOfferAssembler;
import ulaval.glo2003.product.infrastructure.mongodb.entity.OfferEntity;

public class MongoDbOfferRepository implements OfferRepository {
  private final Datastore datastore;
  private final MongoDbOfferAssembler mongoDbOfferAssembler;

  public MongoDbOfferRepository(Datastore datastore, MongoDbOfferAssembler mongoDbOfferAssembler) {
    this.datastore = datastore;
    this.mongoDbOfferAssembler = mongoDbOfferAssembler;
  }

  @Override
  public void save(Offer offer) {
    OfferEntity offerEntity = this.mongoDbOfferAssembler.toEntity(offer);
    this.datastore.save(offerEntity);
  }

  @Override
  public void clear() {
    Query<OfferEntity> query = this.datastore.find(OfferEntity.class);

    query.forEach(this.datastore::delete);
  }
}
