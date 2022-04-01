package ulaval.glo2003.product.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.factory.ProductWithSellerFactory;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerBuilder;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductWithSellerDomainServiceTest {

  private final Seller A_SELLER = new SellerBuilder().build();
  private final Seller ANOTHER_SELLER = new SellerBuilder().build();
  private final SellerId A_SELLER_ID = new SellerId();
  private final SellerId ANOTHER_SELLER_ID = new SellerId();
  private final Product A_PRODUCT = new ProductBuilder().withSellerId(A_SELLER_ID).build();
  private final Product ANOTHER_PRODUCT = new ProductBuilder().withSellerId(ANOTHER_SELLER_ID).build();

  @Mock
  private SellerRepository sellerRepository;

  private final ProductWithSellerFactory productWithSellerFactory = new ProductWithSellerFactory();
  private ProductWithSellerDomainService productSellerService;

  @BeforeEach
  public void setUp() {
    this.productSellerService = new ProductWithSellerDomainService(productWithSellerFactory, sellerRepository);
  }

  @Test
  public void givenAListOfProducts_whenGetProductsWithSeller_thenShouldGetTheirCorrespondingSeller()
          throws GenericException {
    givenASeller(A_SELLER_ID, A_SELLER);
    givenASeller(ANOTHER_SELLER_ID, ANOTHER_SELLER);

    this.productSellerService.getProductsWithSeller(List.of(A_PRODUCT, ANOTHER_PRODUCT));

    verify(this.sellerRepository).findById(A_SELLER_ID);
    verify(this.sellerRepository).findById(ANOTHER_SELLER_ID);
  }

  @Test
  public void givenAListOfProducts_whenGetProductsWithSeller_thenShouldReturnListOfProductWithSeller() throws GenericException {
    givenASeller(A_SELLER_ID, A_SELLER);
    givenASeller(ANOTHER_SELLER_ID, ANOTHER_SELLER);
    ProductWithSeller firstProductWithSeller = new ProductWithSeller(A_PRODUCT, A_SELLER);
    ProductWithSeller secondProductWithSeller = new ProductWithSeller(ANOTHER_PRODUCT, ANOTHER_SELLER);

    List<ProductWithSeller> actual = this.productSellerService.getProductsWithSeller(List.of(A_PRODUCT, ANOTHER_PRODUCT));

    assertEquals(List.of(firstProductWithSeller, secondProductWithSeller), actual);
  }

  @Test
  public void givenAProduct_whenGetProductWithSeller_thenShouldFindSellerById() throws GenericException {
    givenASeller(A_SELLER_ID, A_SELLER);

    this.productSellerService.getProductWithSeller(A_PRODUCT);

    verify(this.sellerRepository).findById(A_SELLER_ID);
  }

  @Test
  public void givenAProduct_whenGetProductWithSeller_thenShouldReturnProductWithSeller() throws GenericException {
    givenASeller(A_SELLER_ID, A_SELLER);
    ProductWithSeller expected = new ProductWithSeller(A_PRODUCT, A_SELLER);

    ProductWithSeller actual = this.productSellerService.getProductWithSeller(A_PRODUCT);

    assertEquals(expected, actual);
  }

  private void givenASeller(SellerId sellerId, Seller seller) throws GenericException {
    given(this.sellerRepository.findById(sellerId)).willReturn(seller);
  }
}