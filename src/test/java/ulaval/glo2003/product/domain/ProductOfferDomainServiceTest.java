package ulaval.glo2003.product.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerBuilder;
import ulaval.glo2003.seller.domain.SellerWithProductsOffersFactory;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductOfferDomainServiceTest {

  private Seller A_SELLER = new SellerBuilder().build();
  private Product A_PRODUCT = new ProductBuilder().build();
  private Offer A_OFFER = new OfferBuilder().build();

  @Mock
  private ProductRepository productRepository;

  @Mock
  private OfferRepository offerRepository;

  @Mock
  private ProductWithOffersFactory productWithOffersFactory;

  @Mock
  private SellerWithProductsOffersFactory sellerWithProductsOffersFactory;

  private ProductOfferDomainService productOfferDomainService;

  @BeforeEach
  public void setUp() {
    this.productOfferDomainService = new ProductOfferDomainService(
            productRepository,
            offerRepository,
            productWithOffersFactory,
            sellerWithProductsOffersFactory
    );
  }

  @Test
  public void givenASellerId_whenAssembleProductsWithOffersToSeller_thenShouldCallTheRepositoryToGetProducts() {
    given(this.productRepository.findBySellerId(this.A_SELLER.getSellerId())).willReturn(new ArrayList<>());

    this.productOfferDomainService.assembleProductsWithOffersToSeller(this.A_SELLER);

    verify(this.productRepository).findBySellerId(this.A_SELLER.getSellerId());
  }

  @Test
  public void givenASellerId_whenAssembleProductsWithOffersToSeller_thenShouldCallTheOfferRepositoryForEachProduct() {
    given(this.productRepository.findBySellerId(this.A_SELLER.getSellerId())).willReturn(this.getProducts());

    this.productOfferDomainService.assembleProductsWithOffersToSeller(this.A_SELLER);

    verify(this.offerRepository).findByProduct(A_PRODUCT);
  }

  @Test
  public void givenASellerId_whenAssembleProductsWithOffersToSeller_thenShouldCallTheProductWithOffersFactory() {
    given(this.productRepository.findBySellerId(this.A_SELLER.getSellerId())).willReturn(this.getProducts());
    given(this.offerRepository.findByProduct(A_PRODUCT)).willReturn(this.getOffers());

    this.productOfferDomainService.assembleProductsWithOffersToSeller(this.A_SELLER);

    verify(this.productWithOffersFactory).create(A_PRODUCT, this.getOffers());
  }

  private List<Product> getProducts() {
    return List.of(A_PRODUCT);
  }

  private List<Offer> getOffers() {
    return List.of(A_OFFER);
  }


}