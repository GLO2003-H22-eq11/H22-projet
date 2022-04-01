package ulaval.glo2003.seller.infrastructure.mongoDb;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.domain.exceptions.SellerNotFoundException;

public class MongoDBSellerRepository implements SellerRepository {
  private final MongoDbSellerAssembler mongoDbSellerAssembler;
  private final Datastore datastore;

  public MongoDBSellerRepository(Datastore datastore, MongoDbSellerAssembler mongoDbSellerAssembler) {
    this.datastore = datastore;
    this.mongoDbSellerAssembler = mongoDbSellerAssembler;
  }

  @Override
  public void save(Seller seller) {
    SellerEntity sellerEntity = this.mongoDbSellerAssembler.toEntity(seller);
    this.datastore.save(sellerEntity);
  }

  @Override
  public Seller findById(SellerId id) throws GenericException {
    SellerEntity sellerEntity = this.datastore.find(SellerEntity.class)
            .filter(Filters.eq("_id", id.toString())).first();

    if (sellerEntity == null) {
      throw new SellerNotFoundException();
    }

    return this.mongoDbSellerAssembler.toSeller(sellerEntity);
  }

  @Override
  public void verifyIfSellerExists(SellerId id) throws SellerNotFoundException {
    SellerEntity sellerEntity = this.datastore.find(SellerEntity.class)
            .filter(Filters.eq("_id", id.toString())).first();

    if (sellerEntity == null) {
      throw new SellerNotFoundException();
    }
  }

  @Override
  public void clear() {
    Query<SellerEntity> query = this.datastore.find(SellerEntity.class);

    query.forEach(this.datastore::delete);
  }
}
