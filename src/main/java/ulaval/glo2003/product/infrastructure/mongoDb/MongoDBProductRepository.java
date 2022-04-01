package ulaval.glo2003.product.infrastructure.mongoDb;

import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.exceptions.ProductNotFoundException;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.ArrayList;
import java.util.List;

public class MongoDBProductRepository implements ProductRepository {

  private final Datastore datastore;
  private final MongoDbProductAssembler mongoDbProductAssembler;

  public MongoDBProductRepository(Datastore datastore, MongoDbProductAssembler mongoDbProductAssembler) {
    this.datastore = datastore;
    this.mongoDbProductAssembler = mongoDbProductAssembler;
  }

  @Override
  public List<Product> findAll() throws GenericException {
    Query<ProductEntity> query = this.datastore.find(ProductEntity.class);

    List<Product> products = new ArrayList<>();

    for (ProductEntity productEntity : query) {
      products.add(this.mongoDbProductAssembler.toDomain(productEntity));
    }

    return products;
  }

  @Override
  public void save(Product product) {
    ProductEntity productEntity = this.mongoDbProductAssembler.toEntity(product);
    this.datastore.save(productEntity);
  }

  @Override
  public Product findById(ProductId id) throws GenericException {
    ProductEntity productEntity = this.datastore.find(ProductEntity.class)
            .filter(Filters.eq("_id", id.toString())).first();


    if (productEntity == null) {
      throw new ProductNotFoundException();
    }

    return this.mongoDbProductAssembler.toDomain(productEntity);
  }

  @Override
  public List<Product> findBySellerId(SellerId sellerId) throws GenericException {
    Query<ProductEntity> query = this.datastore.find(ProductEntity.class)
            .filter(Filters.eq("sellerId", sellerId.toString()));

    List<Product> products = new ArrayList<>();

    for (ProductEntity productEntity : query) {
      products.add(this.mongoDbProductAssembler.toDomain(productEntity));
    }

    return products;
  }

  @Override
  public void clear() {
    Query<ProductEntity> query = this.datastore.find(ProductEntity.class);

    query.forEach(this.datastore::delete);
  }
}
