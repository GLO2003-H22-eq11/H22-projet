package ulaval.glo2003.seller.infrastructure.mongoDb;

import dev.morphia.Datastore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerBuilder;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.exceptions.SellerNotFoundException;
import ulaval.glo2003.seller.infrastructure.mongoDb.MongoDbSellerAssembler;
import ulaval.glo2003.seller.infrastructure.mongoDb.MongoDBSellerRepository;

import static org.junit.jupiter.api.Assertions.*;

class MongoDBSellerRepositoryTest {
  private static final SellerId A_SELLER_ID = new SellerId();
  private static final Seller A_SELLER = new SellerBuilder().withSellerId(A_SELLER_ID).build();

  private static final Datastore datastore = DatastoreProvider.getDatastore();
  private static final MongoDbSellerAssembler mongoDbSellerAssembler = new MongoDbSellerAssembler();
  private static final MongoDBSellerRepository mongoDBSellerRepository = new MongoDBSellerRepository(datastore, mongoDbSellerAssembler);


  @BeforeAll
  public static void setUp() {
    mongoDBSellerRepository.save(A_SELLER);
  }

  @AfterAll
  public static void deleteDatabase() {
    mongoDBSellerRepository.clear();
  }

  @Test
  public void givenASellerAndAnId_whenFindById_thenShouldFindTheSeller() throws GenericException {
    Seller actualSeller = mongoDBSellerRepository.findById(A_SELLER_ID);

    assertEquals(A_SELLER.getSellerId(), actualSeller.getSellerId());
  }

  @Test
  public void givenAnExistentSellerId_whenFindById_thenShouldNotThrow() {
    assertDoesNotThrow(() -> mongoDBSellerRepository.findById(A_SELLER_ID));
  }

  @Test
  public void givenANonExistentSellerId_whenGetSellerById_thenShouldThrowSellerNotFoundException() {
    SellerId aSellerId = new SellerId();

    assertThrows(SellerNotFoundException.class, () -> mongoDBSellerRepository.findById(aSellerId));
  }

  @Test
  public void givenANonExistentSellerId_whenVerifyIfSellerExists_thenShouldThrowSellerNotFoundException() {
    SellerId aSellerId = new SellerId();

    assertThrows(SellerNotFoundException.class, () -> mongoDBSellerRepository.verifyIfSellerExists(aSellerId));
  }

  @Test
  public void givenAnExistentSellerId_whenVerifyIfSellerExists_thenShouldNotThrowSellerNotFoundException() {
    assertDoesNotThrow(() -> mongoDBSellerRepository.verifyIfSellerExists(A_SELLER_ID));
  }

}