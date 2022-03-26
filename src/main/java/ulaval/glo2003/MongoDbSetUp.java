package ulaval.glo2003;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.bson.Document;

public class MongoDbSetUp {

  private static final String DATABASE_URL = System.getenv("DATABASE_URL");
  private static final String DATABASE_NAME = System.getenv("DATABASE_NAME");

  private static final String PRODUCT_ENTITY_PACKAGE = "ulaval.glo2003.product.infrastructure.mongodb.entity";
  private static final String SELLER_ENTITY_PACKAGE = "ulaval.glo2003.seller.infrastructure.mongodb.entity";

  public static Datastore getDatastore() {

    ConnectionString connectionString = new ConnectionString(DATABASE_URL);


    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();

    MongoClient mongoClient = MongoClients.create(settings);

    final Datastore morphiaDatastore = Morphia.createDatastore(mongoClient, DATABASE_NAME);
    morphiaDatastore.ensureIndexes();

    morphiaDatastore.getMapper().mapPackage(PRODUCT_ENTITY_PACKAGE);
    morphiaDatastore.getMapper().mapPackage(SELLER_ENTITY_PACKAGE);


    return morphiaDatastore;
  }
}
