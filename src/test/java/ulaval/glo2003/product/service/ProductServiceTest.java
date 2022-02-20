package ulaval.glo2003.product.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.product.ProductFilterRequest;
import ulaval.glo2003.product.domain.ProductBuilder;
import ulaval.glo2003.product.domain.product.ProductFilters;
import ulaval.glo2003.product.api.product.ProductFiltersFactory;
import ulaval.glo2003.product.domain.product.*;
import ulaval.glo2003.product.domain.product.ProductId;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;


import java.util.LinkedList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  private final String A_SELLER_ID = "S@FG_F$GG$cgwre-fg";
  private String A_TITLE = "TITLE";
  private final List<String> A_CATEGORIES = List.of("A", "B", "C");
  private final int MINIMUM_PRICE = 10;
  private final int MAXIMUM_PRICE = 15;
  private final List<Product> products = new LinkedList<>();

  @Mock
  private ProductRepository productRepository;

  @Mock
  private Product product;

  @Mock
  private SellerRepository sellerRepository;

  @Mock
  private ProductFilterer productFilterer;

  @Mock
  private ProductSellerService productSellerService;

  @Mock
  private ProductFiltersFactory productFiltersFactory;

  private ProductService productService;

  @BeforeEach
  public void setUp() {
    this.productService = new ProductService(
            this.productRepository,
            this.sellerRepository,
            this.productSellerService,
            this.productFilterer
    );
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
  public void givenAProductId_whenGetProductById_thenShouldCallTheRepository() throws GenericException {
    ProductId productId = new ProductId();

    //this.productService.getProductWithSellerById(productId);

    verify(this.productRepository).findById(productId);
  }

  @Test
  public void givenAProductId_whenGetProductById_thenShouldReturnWhatTheRepositoryReturn() throws GenericException {
    ProductId productId = new ProductId();
    given(this.productRepository.findById(productId)).willReturn(product);

    //Product actualProduct = this.productService.getProductWithSellerById(productId);

   // assertEquals(product, actualProduct);
  }

  @Test
  public void givenAnSellerId_whenGetProductOwner_thenShouldCallTheSellerRepository() throws GenericException {
    SellerId sellerId = new SellerId();

    //this.productService.getProductSeller(sellerId);

    verify(this.sellerRepository).findById(sellerId);
  }

  @Test
  public void givenAllInformation_whenGetFilterProducts_thenShouldCallTheProductFilterFactoryToCreate()
          throws GenericException {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, A_TITLE, A_CATEGORIES,
            MINIMUM_PRICE, MAXIMUM_PRICE);
    //this.productService.getFilteredProducts(productFilterRequest);

    //verify(this.productFiltersFactory).create(productFilterRequest);
  }

  @Test
  public void givenAllInformation_whenGetFilterProducts_thenShouldCallTheRepositoryToGetAll() throws GenericException {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, A_TITLE, A_CATEGORIES,
            MINIMUM_PRICE, MAXIMUM_PRICE);

    //this.productService.getFilteredProducts(productFilterRequest);

    verify(this.productRepository).findAll();
  }

  @Test
  public void givenAllInformation_whenGetFilterProducts_thenShouldCallTheProductSorter()  throws GenericException {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, A_TITLE, A_CATEGORIES,
            MINIMUM_PRICE, MAXIMUM_PRICE);

//    BDDMockito.given(productFiltersFactory.create(productFilterRequest))
//            .willReturn(A_PRODUCT_FILTER);
//    BDDMockito.given(this.productRepository.findAll()).willReturn(products);
//
//    this.productService.getFilteredProducts(productFilterRequest);
//
//    verify(this.productSorter).sortProduct(A_PRODUCT_FILTER, products);
  }

  @Test
  public void giveAllInformation_whenGetFilterProducts_thenShouldCallTheProductDomainServiceToGetProductsSeller()
          throws GenericException {
    List<Product> ANOTHER_PRODUCTS = List.of(new ProductBuilder().build());
   // ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, A_TITLE, A_CATEGORIES,
//            MINIMUM_PRICE, MAXIMUM_PRICE);
//    BDDMockito.given(productFiltersFactory.create(productFilterRequest))
//            .willReturn(A_PRODUCT_FILTER);
//    BDDMockito.given(this.productRepository.findAll()).willReturn(products);
//    BDDMockito.given(this.productSorter.sortProduct(A_PRODUCT_FILTER, products)).willReturn(ANOTHER_PRODUCTS);
//
//    this.productService.getFilteredProducts(productFilterRequest);

    verify(this.productSellerService).getProductsWithSeller(ANOTHER_PRODUCTS);
  }

  @Test
  public void giveAllInformation_whenGetFilterProducts_thenShouldReturnWhatDomainServiceReturn()
          throws GenericException {
    ProductFilterRequest productFilterRequest = new ProductFilterRequest(A_SELLER_ID, A_TITLE, A_CATEGORIES,
            MINIMUM_PRICE, MAXIMUM_PRICE);
    List<ProductWithSeller> expectedResult = new LinkedList<>();
    List<Product> ANOTHER_PRODUCTS = List.of(new ProductBuilder().build());
//    BDDMockito.given(productFiltersFactory.create(productFilterRequest))
//            .willReturn(A_PRODUCT_FILTER);
//    BDDMockito.given(this.productRepository.findAll()).willReturn(products);
//    BDDMockito.given(this.productSorter.sortProduct(A_PRODUCT_FILTER, products)).willReturn(ANOTHER_PRODUCTS);
//    BDDMockito.given(this.productService.getFilteredProducts(productFilterRequest)).willReturn(expectedResult);
//
//    List<ProductWithSeller> productWithSellers = this.productService.getFilteredProducts(productFilterRequest);

   // assertEquals(expectedResult, productWithSellers);
  }


  private void givenASellerId(SellerId sellerId) {
    given(this.product.getSellerId()).willReturn(sellerId);
  }
}
