package ulaval.glo2003.products.api;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.products.domain.Product;
import ulaval.glo2003.products.service.ProductService;
import ulaval.glo2003.seller.domain.SellerId;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductResourceTest {

  @Mock
  private Product product;

  @Mock
  private ConstraintsValidator constraintsValidator;

  @Mock
  private ProductFactory productFactory;

  @Mock
  private ProductService productService;

  @Mock
  private ProductRequest productRequest;

  private ProductResource productResource;

  private static final String A_SELLER_STRING_ID = "5a3e3b0b-19a6-46cd-a0fe-bf16f42ba492";
  private static final SellerId A_SELLER_ID = new SellerId("5a3e3b0b-19a6-46cd-a0fe-bf16f42ba492");

  @BeforeEach
  public void setUp() {
    this.productResource = new ProductResource(
            this.constraintsValidator,
            this.productFactory,
            this.productService
    );
  }

  @Test
  public void givenAProductRequestAndASellerId_whenCreateProduct_thenShouldValidateRequest() throws GenericException {
    this.givenAProduct();

    this.productResource.createProduct(productRequest, A_SELLER_STRING_ID);

    verify(this.constraintsValidator).validate(productRequest);
  }

  @Test
  public void givenAProductRequestAndASellerId_whenCreateProduct_thenShouldAddProduct() {
    this.givenAProduct();

    this.productResource.createProduct(productRequest, A_SELLER_STRING_ID);

    verify(this.productService).addProduct(this.product);
  }

  @Test
  public void givenAProductRequestAndASellerId_whenCreateProduct_thenShouldReturnUriWithProductLocation() {
    String endpoint = "products";
    this.givenAProduct();

    URI uri = URI.create(endpoint + "/" + A_SELLER_STRING_ID);

    Response expectedResponse = Response.created(uri).build();
    Response actualResponse = this.productResource.createProduct(productRequest, A_SELLER_STRING_ID);

    assertEquals(expectedResponse.getLocation(), actualResponse.getLocation());
  }


  private void givenAProduct() {
    given(this.productFactory.create(this.productRequest, A_SELLER_STRING_ID)).willReturn(this.product);
    given(this.product.getStringId()).willReturn(A_SELLER_STRING_ID);
  }
}