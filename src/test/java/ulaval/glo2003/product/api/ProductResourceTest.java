package ulaval.glo2003.product.api;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.product.*;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.ProductId;
import ulaval.glo2003.product.domain.product.ProductIdFactory;
import ulaval.glo2003.product.service.ProductService;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.service.SellerService;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductResourceTest {

  private final String A_PRODUCT_ID = "Sqwevwerty";

  @Mock
  private Product product;

  @Mock
  private ProductRequest productRequest;

  @Mock
  private ProductFactory productFactory;

  @Mock
  private ProductService productService;

  @Mock
  private ProductAssembler productAssembler;

  @Mock
  private ProductIdFactory productIdFactory;

  @Mock
  private ProductRequestValidator productRequestValidator;

  private ProductResource productResource;

  private static final String A_SELLER_STRING_ID = "5a3e3b0b-19a6-46cd-a0fe-bf16f42ba492";

  @BeforeEach
  public void setUp() {
    this.productResource = new ProductResource(
            this.productFactory,
            this.productService,
            this.productAssembler,
            this.productIdFactory,
            this.productRequestValidator);
  }

  @Test
  public void givenAProductRequestAndASellerId_whenCreateProduct_thenShouldValidateProductRequest() throws GenericException {
    this.givenAProduct(this.productRequest);

    this.productResource.createProduct(this.productRequest, A_SELLER_STRING_ID);

    verify(this.productRequestValidator).validate(this.productRequest);
  }

  @Test
  public void givenAProductRequestAndASellerId_whenCreateProduct_thenShouldAddProduct() throws GenericException {
    this.givenAProduct(this.productRequest);

    this.productResource.createProduct(this.productRequest, A_SELLER_STRING_ID);

    verify(this.productService).addProduct(this.product);
  }

  @Test
  public void givenAProductRequestAndASellerId_whenCreateProduct_thenShouldReturnUriWithProductLocation() {
    String endpoint = "products";
    this.givenAProduct(this.productRequest);

    URI uri = URI.create(endpoint + "/" + A_SELLER_STRING_ID);

    Response expectedResponse = Response.created(uri).build();
    Response actualResponse = this.productResource.createProduct(this.productRequest, A_SELLER_STRING_ID);

    assertEquals(expectedResponse.getLocation(), actualResponse.getLocation());
  }

  @Test
  public void givenAProductIdParams_whenGetProductById_thenShouldCallTheServiceToGetProduct() throws GenericException {
    ProductId productId = new ProductId();
    given(this.productIdFactory.create(A_PRODUCT_ID)).willReturn(productId);
    given(this.productService.getProductById(productId)).willReturn(product);

    this.productResource.getProductById(A_PRODUCT_ID);

    verify(this.productService).getProductById(productId);
  }

  @Test
  public void givenAProductIdParams_whenGetProductById_thenShouldCallTheServiceToGetOwner() throws GenericException {
    ProductId productId = new ProductId();
    SellerId sellerId = new SellerId();
    given(this.productIdFactory.create(A_PRODUCT_ID)).willReturn(productId);
    given(this.productService.getProductById(productId)).willReturn(product);
    given(product.getSellerId()).willReturn(sellerId);

    this.productResource.getProductById(A_PRODUCT_ID);

    verify(this.productService).getProductOwner(sellerId);
  }


  private void givenAProduct(ProductRequest productRequest) {
    given(this.productFactory.create(productRequest, A_SELLER_STRING_ID)).willReturn(this.product);
    given(this.product.getStringId()).willReturn(A_SELLER_STRING_ID);
  }
}
