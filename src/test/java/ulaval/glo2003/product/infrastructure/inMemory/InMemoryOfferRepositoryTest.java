package ulaval.glo2003.product.infrastructure.inMemory;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.OfferBuilder;
import ulaval.glo2003.product.domain.OfferRepository;
import ulaval.glo2003.product.domain.ProductId;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryOfferRepositoryTest {
  private static final ProductId A_PRODUCT_ID = new ProductId();
  private static final Offer AN_OFFER = new OfferBuilder().withProductId(A_PRODUCT_ID).build();

  private final OfferRepository offerRepository = new InMemoryOfferRepository();

  @Test
  public void givenTwoOffersWithSameProductId_whenFindByProductId_thenShouldFindTwo() {
    Offer anOfferWithSameProductId = new OfferBuilder().withProductId(A_PRODUCT_ID).build();
    this.offerRepository.save(AN_OFFER);
    this.offerRepository.save(anOfferWithSameProductId);

    List<Offer> expected = List.of(AN_OFFER, anOfferWithSameProductId);
    List<Offer> actual = this.offerRepository.findByProductId(A_PRODUCT_ID);

    assertEquals(expected, actual);
  }

  @Test
  public void givenTwoOffersWithDifferentProductId_whenFindByProductId_thenShouldFindOne() {
    ProductId aProductId = new ProductId();
    Offer anOfferWithDifferentProductId = new OfferBuilder().withProductId(aProductId).build();
    this.offerRepository.save(AN_OFFER);
    this.offerRepository.save(anOfferWithDifferentProductId);

    List<Offer> expected = List.of(anOfferWithDifferentProductId);
    List<Offer> actual = this.offerRepository.findByProductId(aProductId);

    assertEquals(expected, actual);
  }
}