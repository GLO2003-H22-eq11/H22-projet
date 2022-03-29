package ulaval.glo2003.seller.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.*;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.domain.SellerWithProducts;
import ulaval.glo2003.seller.domain.SellerWithProductsDomainService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

  private static final SellerId A_SELLER_ID = new SellerId();

  @Mock
  private Seller seller;

  @Mock
  private SellerRepository sellerRepository;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private SellerWithProductsDomainService sellerWithProductsDomainService;

  @Mock
  private SellerWithProducts sellerWithProducts;

  private SellerService sellerService;

  @BeforeEach
  public void setUp() {
    this.sellerService = new SellerService(this.sellerRepository, this.productRepository, this.sellerWithProductsDomainService);
  }

  @Test
  public void givenAMajorSeller_whenAddSeller_thenShouldAddTheSeller() throws GenericException {
    this.sellerService.addSeller(this.seller);

    verify(this.sellerRepository).save(this.seller);
  }

  @Test
  public void givenAMinorSeller_whenAddSeller_thenShouldAddTheSeller() throws GenericException {
    this.sellerService.addSeller(this.seller);

    verify(this.seller).verifyIfMajor();
  }

  @Test
  public void givenASellerId_whenGetSellerById_thenShouldFindById() throws GenericException {
    this.givenASeller(A_SELLER_ID);

    this.sellerService.getSellerById(A_SELLER_ID);

    verify(this.sellerRepository).findById(A_SELLER_ID);
  }

  @Test
  public void givenASellerId_whenGetSellerById_thenShouldFindProductsBySellerId() throws GenericException {
    this.givenASeller(A_SELLER_ID);

    this.sellerService.getSellerById(A_SELLER_ID);

    verify(this.productRepository).findBySellerId(A_SELLER_ID);
  }

  @Test
  public void givenASellerId_whenGetSellerById_thenShouldSetProductsToSeller() throws GenericException {
    this.givenASeller(A_SELLER_ID);
    Product aProduct = new ProductBuilder().build();
    Product anotherProduct = new ProductBuilder().build();
    given(this.productRepository.findBySellerId(A_SELLER_ID)).willReturn(List.of(aProduct, anotherProduct));

    this.sellerService.getSellerById(A_SELLER_ID);

    verify(this.seller).setProducts(List.of(aProduct, anotherProduct));
  }

  @Test
  public void givenASellerId_whenGetSellerWithProductsOffers_thenShouldCallTheRepositoryToGetSeller()
          throws GenericException {
    this.sellerService.getSellerWithProductsById(A_SELLER_ID);

    verify(this.sellerRepository).findById(A_SELLER_ID);
  }

  @Test
  public void givenASellerId_whenGetSellerWithProductsOffers_thenShouldCallTheProductOfferDomainServiceToAssemble()
          throws GenericException {
    given(this.sellerRepository.findById(A_SELLER_ID)).willReturn(this.seller);

    this.sellerService.getSellerWithProductsById(A_SELLER_ID);

    verify(this.sellerWithProductsDomainService).getSellerWithProducts(this.seller);
  }

  @Test
  public void givenASellerId_whenGetSellerWithProductsById_thenShouldReturnSellerWithProducts()
          throws GenericException {
    given(this.sellerRepository.findById(A_SELLER_ID)).willReturn(this.seller);
    given(this.sellerWithProductsDomainService.getSellerWithProducts(this.seller))
            .willReturn(this.sellerWithProducts);

    SellerWithProducts sellerWithProducts = this.sellerService.getSellerWithProductsById(A_SELLER_ID);

    assertEquals(sellerWithProducts, this.sellerWithProducts);
  }

  private void givenASeller(SellerId sellerId) throws GenericException {
    given(this.sellerRepository.findById(sellerId)).willReturn(this.seller);
    given(this.seller.getSellerId()).willReturn(sellerId);
  }
}