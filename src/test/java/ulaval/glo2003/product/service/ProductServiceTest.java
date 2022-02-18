package ulaval.glo2003.product.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.ProductId;
import ulaval.glo2003.product.domain.product.ProductRepository;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private Product product;

  @Mock
  private SellerRepository sellerRepository;

  private ProductService productService;

  @BeforeEach
  public void setUp() {
    this.productService = new ProductService(this.productRepository, this.sellerRepository);
  }

  @Test
  public void givenAProduct_whenAddProduct_thenShouldSaveProduct() throws GenericException {
    this.productService.addProduct(this.product);

    verify(this.productRepository).save(this.product);
  }

  @Test
  public void givenAProduct_whenAddProduct_thenShouldFindProductSellerById() throws GenericException {
    SellerId aSellerId = new SellerId();
    this.givenASellerId(aSellerId);

    this.productService.addProduct(this.product);

    verify(this.sellerRepository).findById(aSellerId);
  }

  @Test
  public void whenAddProduct_thenShouldFindSellerBeforeAddingProduct() throws GenericException {
    SellerId aSellerId = new SellerId();
    this.givenASellerId(aSellerId);

    InOrder inOrder =
            inOrder(this.sellerRepository, this.productRepository);

    this.productService.addProduct(this.product);

    inOrder.verify(this.sellerRepository).findById(aSellerId);
    inOrder.verify(this.productRepository).save(this.product);
  }

  @Test
  public void givenAProductId_whenGetProductById_shouldCallTheRepository() throws GenericException {
    ProductId productId = new ProductId();

    this.productService.getProductById(productId);

    verify(this.productRepository).findById(productId);
  }

  @Test
  public void givenAProductId_whenGetProductById_shouldReturnWhatTheRepositoryReturn() throws GenericException {
    ProductId productId = new ProductId();
    given(this.productRepository.findById(productId)).willReturn(product);

    Product actualProduct = this.productService.getProductById(productId);

    assertEquals(product, actualProduct);
  }
  private void givenASellerId(SellerId sellerId) {
    given(this.product.getSellerId()).willReturn(sellerId);
  }
}
