package ulaval.glo2003.products.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.products.domain.Product;
import ulaval.glo2003.products.domain.ProductRepository;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private Product product;

  private ProductService productService;

  @BeforeEach
  public void setUp() {
    this.productService = new ProductService(this.productRepository);
  }

  @Test
  public void givenAProduct_whenAddProduct_thenShouldSaveProduct() {
    this.productService.addProduct(product);

    verify(this.productRepository).save(product);
  }


}