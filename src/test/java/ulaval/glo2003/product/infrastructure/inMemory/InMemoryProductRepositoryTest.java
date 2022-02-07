package ulaval.glo2003.product.infrastructure.inMemory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductBuilder;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class InMemoryProductRepositoryTest {

  private final InMemoryProductRepository inMemoryProductRepository = new InMemoryProductRepository();

  private final SellerId A_SELLER_ID = new SellerId();
  private final ProductId A_PRODUCT_ID = new ProductId();

  private final SellerId ANOTHER_SELLER_ID = new SellerId();
  private final ProductId ANOTHER_PRODUCT_ID = new ProductId();

  private final Product A_PRODUCT = new ProductBuilder().withSellerId(A_SELLER_ID).withProductId(A_PRODUCT_ID).build();

  @Test
  public void givenAProduct_whenFindBySellerId_thenShouldFindProduct(){
    this.givenAProduct(A_PRODUCT);

    List<Product> actual = this.inMemoryProductRepository.findBySellerId(A_SELLER_ID);

    assertEquals(List.of(A_PRODUCT), actual);
  }

  @Test
  public void givenTwoProductsWithSameSellerId_whenFindBySellerId_thenShouldFindProducts(){
    Product aProductWithSameSellerId = new ProductBuilder().withSellerId(A_SELLER_ID).withProductId(ANOTHER_PRODUCT_ID).build();
    this.givenAProduct(A_PRODUCT);
    this.givenAProduct(aProductWithSameSellerId);

    List<Product> actual = this.inMemoryProductRepository.findBySellerId(A_SELLER_ID);

    assertEquals(List.of(A_PRODUCT, aProductWithSameSellerId), actual);
  }

  @Test
  public void givenTwoProductsWithDifferentSellerId_whenFindBySellerId_thenShouldFindOnlyOneProduct(){
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

  private void givenAProduct(Product product) {
    this.inMemoryProductRepository.save(product);
  }
}