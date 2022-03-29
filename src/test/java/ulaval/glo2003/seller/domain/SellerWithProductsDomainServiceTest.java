package ulaval.glo2003.seller.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.OfferBuilder;
import ulaval.glo2003.product.domain.OfferRepository;
import ulaval.glo2003.product.domain.OffersInformation;
import ulaval.glo2003.product.domain.OffersInformationFactory;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductBuilder;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.ProductWithOffers;
import ulaval.glo2003.product.domain.ProductWithOffersFactory;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SellerWithProductsDomainServiceTest {

  private static final SellerId A_SELLER_ID = new SellerId();
  private static final Seller A_SELLER = new SellerBuilder().withSellerId(A_SELLER_ID).build();
  private static final ProductId A_PRODUCT_ID = new ProductId();
  private static final Product A_PRODUCT = new ProductBuilder().withProductId(A_PRODUCT_ID).build();
  private static final Offer AN_OFFER = new OfferBuilder().build();

  @Mock
  private ProductRepository productRepository;

  @Mock
  private OfferRepository offerRepository;

  private final OffersInformationFactory offersInformationFactory = new OffersInformationFactory();
  private final ProductWithOffersFactory productWithOffersFactory = new ProductWithOffersFactory(offersInformationFactory);

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
  public void givenASeller_whenGetSellerWithProducts_thenShouldCallTheRepositoryToGetProducts() {
    givenProducts(Collections.emptyList());

    this.sellerWithProductsDomainService.getSellerWithProducts(A_SELLER);

    verify(this.productRepository).findBySellerId(A_SELLER.getSellerId());
  }

  @Test
  public void givenASeller_whenGetSellerWithProducts_thenShouldCallTheOfferRepositoryForEachProduct() {
    givenProducts(List.of(A_PRODUCT));

    this.sellerWithProductsDomainService.getSellerWithProducts(A_SELLER);

    verify(this.offerRepository).findByProductId(A_PRODUCT_ID);
  }

  @Test
  public void givenASeller_whenGetSellerWithProducts_thenShouldReturnSellerWithProducts() {
    givenProducts(List.of(A_PRODUCT));
    given(this.offerRepository.findByProductId(A_PRODUCT_ID)).willReturn(List.of(AN_OFFER));

    List<ProductWithOffers> expectedProductWithOffers = List.of(new ProductWithOffers(A_PRODUCT, new OffersInformation(A_PRODUCT.getOffersSummary(), List.of(AN_OFFER))));
    SellerWithProducts expected = new SellerWithProducts(A_SELLER, expectedProductWithOffers);

    SellerWithProducts actual = this.sellerWithProductsDomainService.getSellerWithProducts(A_SELLER);

    assertEquals(expected, actual);
  }

  private void givenProducts(List<Product> products) {
    given(this.productRepository.findBySellerId(A_SELLER.getSellerId())).willReturn(products);
  }
}