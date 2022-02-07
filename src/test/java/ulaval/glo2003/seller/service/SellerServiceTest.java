package ulaval.glo2003.seller.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductBuilder;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.domain.exceptions.SellerNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

  @Mock
  private Seller seller;

  @Mock
  private SellerRepository sellerRepository;

  @Mock
  private ProductRepository productRepository;

  private SellerService sellerService;

  @BeforeEach
  public void setUp() {
    this.sellerService = new SellerService(this.sellerRepository, this.productRepository);
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
    SellerId aSellerId = new SellerId();
    this.givenASeller(aSellerId);

    this.sellerService.getSellerById(aSellerId);

    verify(this.sellerRepository).findById(aSellerId);
  }

  @Test
  public void givenASellerId_whenGetSellerById_thenShouldFindProductsBySellerId() throws GenericException {
    SellerId aSellerId = new SellerId();
    this.givenASeller(aSellerId);

    this.sellerService.getSellerById(aSellerId);

    verify(this.productRepository).findBySellerId(aSellerId);
  }

  @Test
  public void givenASellerId_whenGetSellerById_thenShouldSetProductsToSeller() throws GenericException {
    SellerId aSellerId = new SellerId();
    this.givenASeller(aSellerId);
    Product aProduct = new ProductBuilder().build();
    Product anotherProduct = new ProductBuilder().build();
    given(this.productRepository.findBySellerId(aSellerId)).willReturn(List.of(aProduct, anotherProduct));

    this.sellerService.getSellerById(aSellerId);

    verify(this.seller).setProducts(List.of(aProduct, anotherProduct));
  }

  private void givenASeller(SellerId sellerId) throws GenericException {
    given(this.sellerRepository.findById(sellerId)).willReturn(this.seller);
    given(this.seller.getSellerId()).willReturn(sellerId);
  }
}