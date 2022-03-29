package ulaval.glo2003.product.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerBuilder;
import ulaval.glo2003.seller.domain.SellerWithProductsDomainService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SellerWithProductsDomainServiceTest {

  private Seller A_SELLER = new SellerBuilder().build();
  private ProductId A_PRODUCT_ID = new ProductId();
  private Product A_PRODUCT = new ProductBuilder().withProductId(A_PRODUCT_ID).build();
  private Offer A_OFFER = new OfferBuilder().build();

  @Mock
  private ProductRepository productRepository;

  @Mock
  private OfferRepository offerRepository;

  @Mock
  private ProductWithOffersFactory productWithOffersFactory;

  private SellerWithProductsDomainService sellerWithProductsDomainService;

  @BeforeEach
  public void setUp() {
    this.sellerWithProductsDomainService = new SellerWithProductsDomainService(
            productRepository,
            offerRepository,
            productWithOffersFactory
    );
  }

  @Test
  public void givenASellerId_whenAssembleProductsWithOffersToSeller_thenShouldCallTheRepositoryToGetProducts() {
    given(this.productRepository.findBySellerId(this.A_SELLER.getSellerId())).willReturn(new ArrayList<>());

    this.sellerWithProductsDomainService.getSellerWithProducts(this.A_SELLER);

    verify(this.productRepository).findBySellerId(this.A_SELLER.getSellerId());
  }

  @Test
  public void givenASellerId_whenAssembleProductsWithOffersToSeller_thenShouldCallTheOfferRepositoryForEachProduct() {
    given(this.productRepository.findBySellerId(this.A_SELLER.getSellerId())).willReturn(this.getProducts());

    this.sellerWithProductsDomainService.getSellerWithProducts(this.A_SELLER);

    verify(this.offerRepository).findByProductId(A_PRODUCT_ID);
  }

  @Test
  public void givenASellerId_whenAssembleProductsWithOffersToSeller_thenShouldCallTheProductWithOffersFactory() {
    given(this.productRepository.findBySellerId(this.A_SELLER.getSellerId())).willReturn(this.getProducts());
    given(this.offerRepository.findByProductId(A_PRODUCT_ID)).willReturn(this.getOffers());

    this.sellerWithProductsDomainService.getSellerWithProducts(this.A_SELLER);

    verify(this.productWithOffersFactory).create(A_PRODUCT, this.getOffers());
  }

  private List<Product> getProducts() {
    return List.of(A_PRODUCT);
  }

  private List<Offer> getOffers() {
    return List.of(A_OFFER);
  }


}