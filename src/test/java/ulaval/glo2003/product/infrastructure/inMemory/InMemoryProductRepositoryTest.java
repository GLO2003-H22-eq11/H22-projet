package ulaval.glo2003.product.infrastructure.inMemory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.exceptions.ProductNotFoundException;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.ProductBuilder;
import ulaval.glo2003.product.domain.product.productId.ProductId;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InMemoryProductRepositoryTest {

  private final InMemoryProductRepository inMemoryProductRepository = new InMemoryProductRepository();

  private final SellerId A_SELLER_ID = new SellerId();
  private final ProductId A_PRODUCT_ID = new ProductId();

  private final SellerId ANOTHER_SELLER_ID = new SellerId();
  private final ProductId ANOTHER_PRODUCT_ID = new ProductId();

  private final Product A_PRODUCT = new ProductBuilder().withSellerId(A_SELLER_ID).withProductId(A_PRODUCT_ID).build();
  private final Product ANOTHER_PRODUCT = new ProductBuilder()
          .withSellerId(ANOTHER_SELLER_ID).withProductId(ANOTHER_PRODUCT_ID).build();

  @Mock
  private Product product;

  @Test
  public void givenAProduct_whenFindBySellerId_thenShouldFindProduct() {
    this.givenAProduct(A_PRODUCT);

    List<Product> actual = this.inMemoryProductRepository.findBySellerId(A_SELLER_ID);

    assertEquals(List.of(A_PRODUCT), actual);
  }

  @Test
  public void givenTwoProductsWithSameSellerId_whenFindBySellerId_thenShouldFindProducts() {
    Product aProductWithSameSellerId = new ProductBuilder().withSellerId(A_SELLER_ID).withProductId(ANOTHER_PRODUCT_ID).build();
    this.givenAProduct(A_PRODUCT);
    this.givenAProduct(aProductWithSameSellerId);

    List<Product> actual = this.inMemoryProductRepository.findBySellerId(A_SELLER_ID);

    assertEquals(List.of(A_PRODUCT, aProductWithSameSellerId), actual);
  }

  @Test
  public void givenTwoProductsWithDifferentSellerId_whenFindBySellerId_thenShouldFindOnlyOneProduct() {
    Product aProductWithADifferentSellerId = new ProductBuilder().withSellerId(ANOTHER_SELLER_ID).withProductId(A_PRODUCT_ID).build();
    this.givenAProduct(A_PRODUCT);
    this.givenAProduct(aProductWithADifferentSellerId);

    List<Product> actual = this.inMemoryProductRepository.findBySellerId(A_SELLER_ID);

    assertEquals(List.of(A_PRODUCT), actual);
  }

  @Test
  public void givenNoProducts_whenFindBySellerId_thenShouldReturnEmptyList() {
    List<Product> actual = this.inMemoryProductRepository.findBySellerId(A_SELLER_ID);

    assertTrue(actual.isEmpty());
  }

  @Test
  public void givenNoProduct_whenFindProductById_thenShouldThrowException() {
    ProductId productId = new ProductId();

    assertThrows(ProductNotFoundException.class, () -> this.inMemoryProductRepository.findById(productId));
  }

  @Test
  public void givenAnProduct_whenFindById_thenShouldReturnTheProduct() throws GenericException {
    ProductId productId = new ProductId();
    SellerId sellerId = new SellerId();
    given(product.getProductId()).willReturn(productId);
    given(product.getSellerId()).willReturn(sellerId);

    this.inMemoryProductRepository.save(product);

    Product actualProduct = this.inMemoryProductRepository.findById(productId);

    assertEquals(product, actualProduct);
  }

  @Test
  public void givenTwoProduct_whenGetAll_thenShouldReturnTheTwoProduct() {
    this.inMemoryProductRepository.save(A_PRODUCT);
    this.inMemoryProductRepository.save(ANOTHER_PRODUCT);

    List<Product> products = this.inMemoryProductRepository.findAll();

    assertEquals(products.size(), 2);
  }

  private void givenAProduct(Product product) {
    this.inMemoryProductRepository.save(product);
  }
}