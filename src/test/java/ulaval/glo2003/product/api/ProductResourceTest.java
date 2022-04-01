package ulaval.glo2003.product.api;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.assembler.ProductAssembler;
import ulaval.glo2003.product.api.request.OfferRequest;
import ulaval.glo2003.product.api.request.ProductRequest;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.product.api.response.ProductsResponse;
import ulaval.glo2003.product.api.validator.OfferRequestValidator;
import ulaval.glo2003.product.api.validator.ProductRequestValidator;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.factory.OfferFactory;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.factory.ProductFactory;
import ulaval.glo2003.product.domain.ProductFilters;
import ulaval.glo2003.product.domain.factory.ProductFiltersFactory;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.domain.factory.ProductIdFactory;
import ulaval.glo2003.product.domain.ProductWithSeller;
import ulaval.glo2003.product.service.ProductService;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductResourceTest {

  private final String A_SELLER_ID = "S@FG_F$GG$cgwre-fg";
  private final String A_TITLE = "TITLE";
  private final List<String> STRING_CATEGORIES = List.of("A", "B", "C");
  private final Double MINIMUM_PRICE = 10.0;
  private final Double MAXIMUM_PRICE = 15.0;
  private static final String A_PRODUCT_ID = "Sqwevwerty";
  private static final String A_SELLER_STRING_ID = "5a3e3b0b-19a6-46cd-a0fe-bf16f42ba492";
  private static final String A_NAME = "A NAME";
  private static final String AN_EMAIL = "allo@email.ca";
  private static final String A_PHONE_NUMBER = "14181234567";
  private static final Double AN_AMOUNT = 20.0;
  private static final String A_MESSAGE = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.";

  private final OfferRequest AN_OFFER_REQUEST = this.givenAnOfferRequest(A_NAME, AN_EMAIL, A_PHONE_NUMBER, AN_AMOUNT, A_MESSAGE);
  private final ProductFilters A_PRODUCT_FILTERS = new ProductFilters();

  @Mock
  private Product product;

  @Mock
  private ProductResponse productResponse;

  @Mock
  private ProductWithSeller productWithSeller;

  @Mock
  private ProductRequest productRequest;

  @Mock
  private Offer offer;

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

  @Mock
  private OfferRequestValidator offerRequestValidator;

  @Mock
  private OfferFactory offerFactory;

  private ProductResource productResource;


  @BeforeEach
  public void setUp() {
    this.productResource = new ProductResource(
            this.productFactory,
            this.productService,
            this.productAssembler,
            this.productIdFactory,
            this.productRequestValidator,
            this.productFiltersFactory,
            this.offerFactory,
            this.offerRequestValidator);
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
  public void givenAProductRequestAndASellerId_whenCreateProduct_thenShouldReturnUriWithProductLocation() throws GenericException {
    String endpoint = "products";
    this.givenAProduct(this.productRequest);

    URI uri = URI.create(endpoint + "/" + A_SELLER_STRING_ID);

    Response expectedResponse = Response.created(uri).build();
    Response actualResponse = this.productResource.createProduct(this.productRequest, A_SELLER_STRING_ID);

    assertEquals(expectedResponse.getLocation(), actualResponse.getLocation());
  }

  @Test
  public void givenAProductIdParams_whenGetProductById_thenShouldGetProductWithSeller() throws GenericException {
    ProductId productId = new ProductId();
    given(this.productIdFactory.create(A_PRODUCT_ID)).willReturn(productId);
    given(this.productService.getProductWithSeller(productId)).willReturn(this.productWithSeller);

    this.productResource.getProductById(A_PRODUCT_ID);

    verify(this.productService).getProductWithSeller(productId);
  }


  @Test
  public void givenASellerIdATitleACategoriesAMinimumPriceAndAMaximumPrice_whenGetFilteredProducts_thenShouldGetFilteredProducts() throws GenericException {
    this.givenAListOfProductsWithSeller(A_PRODUCT_FILTERS);

    this.productResource.getFilteredProducts(A_SELLER_ID, A_TITLE, STRING_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE);

    verify(this.productService).getFilteredProducts(A_PRODUCT_FILTERS);
  }

  @Test
  public void givenASellerIdATitleACategoriesAMinimumPriceAndAMaximumPrice_whenGetFilteredProducts_thenShouldReturnEntity() throws GenericException {
    ProductsResponse aProductsResponse = new ProductsResponse(List.of(productResponse));
    this.givenAListOfProductsWithSeller(A_PRODUCT_FILTERS);
    given(this.productAssembler.toProductsResponse(List.of(productWithSeller))).willReturn(aProductsResponse);
    Response expected = Response.ok().entity(aProductsResponse).build();

    Response actual = this.productResource.getFilteredProducts(A_SELLER_ID, A_TITLE, STRING_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE);

    assertEquals(expected.getEntity(), actual.getEntity());
  }

  @Test
  public void whenGetFilteredProducts_thenShouldValidateMinPriceAndMaxPrice() throws GenericException {
    this.productResource.getFilteredProducts(A_SELLER_ID, A_TITLE, STRING_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE);

    verify(this.productRequestValidator).validatePrices(MINIMUM_PRICE, MAXIMUM_PRICE);
  }

  @Test
  public void givenAProductIdAndAnOfferRequest_whenCreateOffer_thenShouldValidateOfferRequest() throws GenericException {
    givenAnOffer(offer, A_PRODUCT_ID);

    this.productResource.createOffer(AN_OFFER_REQUEST, A_PRODUCT_ID);

    verify(this.offerRequestValidator).validate(AN_OFFER_REQUEST);
  }

  @Test
  public void givenAProductIdAndAnOfferRequest_whenCreateOffer_thenShouldCreateOffer() throws GenericException {
    givenAnOffer(offer, A_PRODUCT_ID);

    this.productResource.createOffer(AN_OFFER_REQUEST, A_PRODUCT_ID);

    verify(this.productService).createOffer(offer);
  }

  private void givenAProduct(ProductRequest productRequest) throws GenericException {
    given(this.productFactory.create(
                    A_SELLER_STRING_ID,
                    productRequest.title,
                    productRequest.description,
                    productRequest.suggestedPrice,
                    productRequest.categories
            )
    ).willReturn(this.product);
    given(this.product.getStringId()).willReturn(A_SELLER_STRING_ID);
  }

  private void givenAListOfProductsWithSeller(ProductFilters productFilters) throws GenericException {
    given(this.productFiltersFactory.create(A_SELLER_ID, A_TITLE, STRING_CATEGORIES, MINIMUM_PRICE, MAXIMUM_PRICE)).willReturn(productFilters);
    given(this.productService.getFilteredProducts(productFilters)).willReturn(List.of(productWithSeller));
  }

  private void givenAnOffer(Offer offer, String productId) throws GenericException {
    given(this.offerFactory.create(A_NAME, AN_EMAIL, A_PHONE_NUMBER, AN_AMOUNT, A_MESSAGE, productId)).willReturn(offer);
  }

  private OfferRequest givenAnOfferRequest(String name, String email, String phoneNumber, Double amount, String message) {
    OfferRequest offerRequest = new OfferRequest();
    offerRequest.name = name;
    offerRequest.email = email;
    offerRequest.phoneNumber = phoneNumber;
    offerRequest.amount = amount;
    offerRequest.message = message;

    return offerRequest;
  }
}
