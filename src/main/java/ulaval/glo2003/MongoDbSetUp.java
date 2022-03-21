package ulaval.glo2003;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class MongoDbSetUp {

  private static final String MONGODB_CONNECTION = "mongodb+srv://MarinoPositivoCovido:PeQbLsZ3qT3EaZQ@floppa."
         + "cuxcq.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

  private static final String LOCAL_DATABASE = "floppa-dev";
  private static final String PRODUCTION_DATABASE = "floppa-production";
  private static final String STAGING_DATABASE = "floppa-staging";
  private static final String PRODUCT_ENTITY_PACKAGE = "ulaval.glo2003.product.infrastructure.mongodb.entity";
  private static final String SELLER_ENTITY_PACKAGE = "ulaval.glo2003.seller.infrastructure.mongodb.entity";

  public static Datastore getDatastore() {

    ConnectionString connectionString = new ConnectionString(
            MONGODB_CONNECTION);
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();
    MongoClient mongoClient = MongoClients.create(settings);

    final Datastore morphiaDatastore = Morphia.createDatastore(mongoClient, LOCAL_DATABASE);

    morphiaDatastore.getMapper().mapPackage(PRODUCT_ENTITY_PACKAGE);
    morphiaDatastore.getMapper().mapPackage(SELLER_ENTITY_PACKAGE);

    morphiaDatastore.ensureIndexes();

    return morphiaDatastore;
  }
}
