package ulaval.glo2003.product.api;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.product.*;
import ulaval.glo2003.product.api.product.response.ProductsResponse;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.productId.ProductId;
import ulaval.glo2003.product.domain.product.productId.ProductIdFactory;
import ulaval.glo2003.product.domain.product.productWithSeller.ProductWithSeller;
import ulaval.glo2003.product.service.ProductService;
import ulaval.glo2003.seller.domain.SellerId;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductResourceTest {

  private final String A_SELLER_ID = "S@FG_F$GG$cgwre-fg";
  private String A_TITLE = "TITLE";
  private final List<String> A_CATEGORIES = List.of("A", "B", "C");
  private final int MINIMUM_PRICE = 10;
  private final int MAXIMUM_PRICE = 15;
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
            this.productRequestValidator
    );
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

  @Test
  public void givenAllInformation_whenGetFilterProducts_thenShouldCallTheProductService() throws GenericException {
    this.productResource.getFilterProducts(A_SELLER_ID, A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE);

    verify(this.productService).getFilterProducts(A_SELLER_ID, A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE);
  }

  @Test
  public void givenAllInformation_whenGetFilteredProducts_thenShouldCallTheProductsAssembler() throws GenericException {
    List<ProductWithSeller> productWithSellers = new LinkedList<>();
    given(this.productService.getFilterProducts(A_SELLER_ID, A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE))
            .willReturn(productWithSellers);

    this.productResource.getFilterProducts(A_SELLER_ID, A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE);

    verify(this.productAssembler).toProductsResponse(productWithSellers);
  }

  @Test
  public void givenAllInformation_whenGetFilteredProducts_thenShouldReturnTheRightResponse() throws GenericException {
    ProductsResponse productsResponse = new ProductsResponse();
    List<ProductWithSeller> productWithSellers = new LinkedList<>();
    given(this.productService.getFilterProducts(A_SELLER_ID, A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE))
            .willReturn(productWithSellers);
    given(this.productAssembler.toProductsResponse(productWithSellers)).willReturn(productsResponse);

    Response response = this.productResource.getFilterProducts(A_SELLER_ID,
            A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE);

    assertEquals(response.getEntity(), productsResponse);
  }


  private void givenAProduct(ProductRequest productRequest) {
    given(this.productFactory.create(productRequest, A_SELLER_STRING_ID)).willReturn(this.product);
    given(this.product.getStringId()).willReturn(A_SELLER_STRING_ID);
  }
}
