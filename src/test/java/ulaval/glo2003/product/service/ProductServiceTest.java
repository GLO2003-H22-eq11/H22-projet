package ulaval.glo2003.product.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.*;
import ulaval.glo2003.product.domain.exceptions.ProductNotFoundException;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  private static final ProductId A_PRODUCT_ID = new ProductId();
  private static final Amount AN_AMOUNT = Amount.fromDouble(20.0);
  private static final Offer AN_OFFER = new OfferBuilder().withProductId(A_PRODUCT_ID).withAmount(AN_AMOUNT).build();

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ProductFilters productFilters;

  @Mock
  private Product product;

  @Mock
  private ProductWithSeller productWithSeller;

  @Mock
  private SellerRepository sellerRepository;

  @Mock
  private ProductFilterer productFilterer;

  @Mock
  private ProductWithSellerDomainService productSellerService;

  @Mock
  private OfferRepository offerRepository;

  private ProductService productService;

  @BeforeEach
  public void setUp() {
    this.productService = new ProductService(
            this.productRepository,
            this.sellerRepository,
            this.productSellerService,
            this.productFilterer,
            this.offerRepository
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

    verify(this.sellerRepository).verifyIfSellerExists(aSellerId);
  }

  @Test
  public void whenAddProduct_thenShouldFindSellerBeforeAddingProduct() throws GenericException {
    SellerId aSellerId = new SellerId();
    this.givenASellerId(aSellerId);

    InOrder inOrder =
            inOrder(this.sellerRepository, this.productRepository);

    this.productService.addProduct(this.product);

    inOrder.verify(this.sellerRepository).verifyIfSellerExists(aSellerId);
    inOrder.verify(this.productRepository).save(this.product);
  }

  @Test
  public void givenAProductId_whenGetProductWithSeller_thenShouldFindProduct() throws GenericException {
    this.productService.getProductWithSeller(A_PRODUCT_ID);

    verify(this.productRepository).findById(A_PRODUCT_ID);
  }

  @Test
  public void givenAProductId_whenGetProductWithSeller_thenShouldReturnProductWithSeller() throws GenericException {
    givenAProduct(A_PRODUCT_ID);
    given(this.productSellerService.getProductWithSeller(product)).willReturn(productWithSeller);

    ProductWithSeller actual = this.productService.getProductWithSeller(A_PRODUCT_ID);

    assertEquals(productWithSeller, actual);
  }

  @Test
  public void givenProductFilters_whenGetFilteredProducts_thenShouldFindFilteredProducts() throws GenericException {
    this.productService.getFilteredProducts(productFilters);

    verify(this.productFilterer).findFilteredProducts(productFilters);
  }

  @Test
  public void givenProductFiltersWithASellerId_whenGetFilteredProducts_thenShouldVerifyThatSellerIdExists() throws GenericException {
    SellerId aSellerId = new SellerId();
    given(productFilters.getSellerId()).willReturn(aSellerId);
    this.productService.getFilteredProducts(productFilters);

    verify(this.sellerRepository).verifyIfSellerExists(aSellerId);
  }

  @Test
  public void givenProductFiltersWithNoSellerId_whenGetFilteredProducts_thenShouldNotVerifyThatSellerIdExists() throws GenericException {
    given(productFilters.getSellerId()).willReturn(null);
    this.productService.getFilteredProducts(productFilters);

    verify(this.sellerRepository, never()).verifyIfSellerExists(any(SellerId.class));
  }

  @Test
  public void givenProductFilters_whenGetFilteredProducts_thenShouldReturnProductsWithSellers() throws GenericException {
    given(this.productFilterer.findFilteredProducts(productFilters)).willReturn(List.of(product));
    given(this.productSellerService.getProductsWithSeller(List.of(product))).willReturn(List.of(productWithSeller));

    List<ProductWithSeller> actual = this.productService.getFilteredProducts(productFilters);

    assertEquals(List.of(productWithSeller), actual);
  }

  @Test
  public void givenAnOfferAndAProductId_whenCreateOffer_thenShouldFindProductById() throws GenericException {
    givenAProduct(A_PRODUCT_ID);

    this.productService.createOffer(AN_OFFER);

    verify(this.productRepository).findById(A_PRODUCT_ID);
  }

  @Test
  public void givenAnOfferAndAProductId_whenCreateOffer_thenShouldAddOfferToProduct() throws GenericException {
    givenAProduct(A_PRODUCT_ID);

    this.productService.createOffer(AN_OFFER);

    verify(this.product).addOfferAmount(AN_AMOUNT);
  }

  @Test
  public void givenAnOfferAndAProductId_whenCreateOffer_thenShouldSaveProduct() throws GenericException {
    givenAProduct(A_PRODUCT_ID);

    this.productService.createOffer(AN_OFFER);

    verify(this.productRepository).save(product);
  }

  @Test
  public void givenAnOfferAndAProductId_whenCreateOffer_thenShouldSaveOffer() throws GenericException {
    givenAProduct(A_PRODUCT_ID);

    this.productService.createOffer(AN_OFFER);

    verify(this.offerRepository).save(AN_OFFER);
  }

  private void givenASellerId(SellerId sellerId) {
    given(this.product.getSellerId()).willReturn(sellerId);
  }

  private void givenAProduct(ProductId productId) throws ProductNotFoundException {
    given(this.productRepository.findById(productId)).willReturn(product);
  }
}
