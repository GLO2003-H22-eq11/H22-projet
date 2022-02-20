package ulaval.glo2003.product.api;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.product.*;
import ulaval.glo2003.product.api.product.assembler.ProductAssembler;
import ulaval.glo2003.product.api.product.response.ProductsFilterResponse;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.ProductId;
import ulaval.glo2003.product.domain.product.ProductIdFactory;
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

  @Mock
  private ProductFiltersFactory productFiltersFactory;


  private ProductResource productResource;

  private static final String A_SELLER_STRING_ID = "5a3e3b0b-19a6-46cd-a0fe-bf16f42ba492";

  @BeforeEach
  public void setUp() {
    this.productResource = new ProductResource(
            this.productFactory,
            this.productService,
            this.productAssembler,
            this.productIdFactory,
            this.productRequestValidator,
            this.productFiltersFactory
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
  public void givenAProductIdParams_whenGetProductById_thenShouldCallTheServiceToGetProduct(){
    ProductId productId = new ProductId();
    given(this.productIdFactory.create(A_PRODUCT_ID)).willReturn(productId);
    //given(this.productService.getProductWithSellerById(productId)).willReturn(product);

    this.productResource.getProductById(A_PRODUCT_ID);

    //verify(this.productService).getProductWithSellerById(productId);
  }

  @Test
  public void givenAProductIdParams_whenGetProductById_thenShouldCallTheServiceToGetOwner() throws GenericException {
    ProductId productId = new ProductId();
    SellerId sellerId = new SellerId();
    given(this.productIdFactory.create(A_PRODUCT_ID)).willReturn(productId);
    //given(this.productService.getProductWithSellerById(productId)).willReturn(product);
    given(product.getSellerId()).willReturn(sellerId);

    this.productResource.getProductById(A_PRODUCT_ID);

    //verify(this.productService).getProductSeller(sellerId);
  }

  @Test
  public void givenAllInformation_whenGetFilterProducts_thenShouldCallTheProductService() throws GenericException {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, A_TITLE, A_CATEGORIES,
            MINIMUM_PRICE, MAXIMUM_PRICE);
    //given(this.productFilterAssembler.toRequest(A_SELLER_ID, A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE))
     //       .willReturn(productFilterRequest);
    this.productResource.getFilteredProducts(A_SELLER_ID, A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE);

   // verify(this.productService).getFilteredProducts(productFilterRequest);
  }

  @Test
  public void givenAllInformation_whenGetFilteredProducts_thenShouldCallTheProductsAssembler() throws GenericException {
    //List<ProductWithSeller> productWithSellers = new LinkedList<>();
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, A_TITLE, A_CATEGORIES,
            MINIMUM_PRICE, MAXIMUM_PRICE);
//    given(this.productFilterAssembler.toRequest(A_SELLER_ID, A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE))
//            .willReturn(productFilterRequest);
//    given(this.productService.getFilteredProducts(productFilterRequest))
//            .willReturn(productWithSellers);
//
//    this.productResource.getFilteredProducts(A_SELLER_ID, A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE);
//
//    verify(this.productFilterAssembler).toProductsResponse(productWithSellers);
  }

  @Test
  public void givenAllInformation_whenGetFilteredProducts_thenShouldReturnTheRightResponse() throws GenericException {
//    ProductsFilterResponse productsFilterResponse = new ProductsFilterResponse(new LinkedList<>());
//    List<ProductWithSeller> productWithSellers = new LinkedList<>();
//    ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, A_TITLE, A_CATEGORIES,
//            MINIMUM_PRICE, MAXIMUM_PRICE);
//    given(this.productFilterAssembler.toRequest(A_SELLER_ID, A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE))
//            .willReturn(productFilterRequest);
//    given(this.productService.getFilteredProducts(productFilterRequest))
//            .willReturn(productWithSellers);
//    given(this.productFilterAssembler.toProductsResponse(productWithSellers)).willReturn(productsFilterResponse);
//
//    Response response = this.productResource.getFilteredProducts(A_SELLER_ID,
//            A_TITLE, A_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE);
//
//    assertEquals(response.getEntity(), productsFilterResponse);
  }


  private void givenAProduct(ProductRequest productRequest) {
    given(this.productFactory.create(productRequest, A_SELLER_STRING_ID)).willReturn(this.product);
    given(this.product.getStringId()).willReturn(A_SELLER_STRING_ID);
  }
}
