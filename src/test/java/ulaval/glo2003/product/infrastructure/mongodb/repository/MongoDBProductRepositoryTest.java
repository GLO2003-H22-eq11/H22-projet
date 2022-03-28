package ulaval.glo2003.product.infrastructure.mongodb.repository;

import dev.morphia.Datastore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.context.DatastoreProvider;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductBuilder;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.domain.exceptions.ProductNotFoundException;
import ulaval.glo2003.product.infrastructure.mongodb.MongoDbProductAssembler;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MongoDBProductRepositoryTest {
  private final SellerId A_SELLER_ID = new SellerId();
  private final ProductId A_PRODUCT_ID = new ProductId();

  private final SellerId ANOTHER_SELLER_ID = new SellerId();
  private final ProductId ANOTHER_PRODUCT_ID = new ProductId();

  private final Product A_PRODUCT = new ProductBuilder().withSellerId(A_SELLER_ID).withProductId(A_PRODUCT_ID).build();
  private final Product ANOTHER_PRODUCT = new ProductBuilder()
          .withSellerId(ANOTHER_SELLER_ID).withProductId(ANOTHER_PRODUCT_ID).build();

  private final Datastore datastore = DatastoreProvider.getDatastore();
  private final MongoDbProductAssembler mongoDbProductAssembler = new MongoDbProductAssembler();

  private final MongoDBProductRepository mongoDBProductRepository = new MongoDBProductRepository(datastore, mongoDbProductAssembler);


  @BeforeEach
  public void clearDatabase() {
    this.mongoDBProductRepository.clear();
  }

  @Test
  public void givenAProduct_whenFindBySellerId_thenShouldFindProduct() throws GenericException {
    this.givenAProduct(A_PRODUCT);

    List<Product> actual = this.mongoDBProductRepository.findBySellerId(A_SELLER_ID);

    assertEquals(List.of(A_PRODUCT), actual);
  }

  @Test
  public void givenTwoProductsWithSameSellerId_whenFindBySellerId_thenShouldFindProducts() throws GenericException {
    Product aProductWithSameSellerId = new ProductBuilder().withSellerId(A_SELLER_ID).withProductId(ANOTHER_PRODUCT_ID).build();
    this.givenAProduct(A_PRODUCT);
    this.givenAProduct(aProductWithSameSellerId);

    List<Product> actual = this.mongoDBProductRepository.findBySellerId(A_SELLER_ID);

    assertEquals(List.of(A_PRODUCT, aProductWithSameSellerId), actual);
  }

  @Test
  public void givenTwoProductsWithDifferentSellerId_whenFindBySellerId_thenShouldFindOnlyOneProduct() throws GenericException {
    Product aProductWithADifferentSellerId = new ProductBuilder().withSellerId(ANOTHER_SELLER_ID).withProductId(ANOTHER_PRODUCT_ID).build();
    this.givenAProduct(A_PRODUCT);
    this.givenAProduct(aProductWithADifferentSellerId);

    List<Product> actual = this.mongoDBProductRepository.findBySellerId(A_SELLER_ID);

    assertEquals(List.of(A_PRODUCT), actual);
  }

  @Test
  public void givenNoProducts_whenFindBySellerId_thenShouldReturnEmptyList() throws GenericException {
    List<Product> actual = this.mongoDBProductRepository.findBySellerId(A_SELLER_ID);

    assertTrue(actual.isEmpty());
  }

  @Test
  public void givenNoProduct_whenFindProductById_thenShouldThrowException() {
    ProductId productId = new ProductId();

    assertThrows(ProductNotFoundException.class, () -> this.mongoDBProductRepository.findById(productId));
  }

  @Test
  public void givenAProduct_whenFindById_thenShouldReturnTheProduct() throws GenericException {
    this.mongoDBProductRepository.save(A_PRODUCT);

    Product actualProduct = this.mongoDBProductRepository.findById(A_PRODUCT_ID);

    assertEquals(A_PRODUCT, actualProduct);
  }

  @Test
  public void givenTwoProduct_whenGetAll_thenShouldReturnTheTwoProduct() throws GenericException {
    this.mongoDBProductRepository.save(A_PRODUCT);
    this.mongoDBProductRepository.save(ANOTHER_PRODUCT);

    List<Product> products = this.mongoDBProductRepository.findAll();

    assertEquals(products.size(), 2);
  }


  private void givenAProduct(Product product) {
    this.mongoDBProductRepository.save(product);
  }
}