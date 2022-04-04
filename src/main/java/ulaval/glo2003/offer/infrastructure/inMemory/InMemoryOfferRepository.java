package ulaval.glo2003.offer.infrastructure.inMemory;

import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.OfferRepository;
import ulaval.glo2003.product.domain.ProductId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryOfferRepository implements OfferRepository {
  private HashMap<ProductId, List<Offer>> offersByProductId = new HashMap<>();

  @Override
  public void save(Offer offer) {
    this.offersByProductId.computeIfAbsent(offer.getProductId(), k -> new ArrayList<>()).add(offer);
  }

  @Override
  public List<Offer> findByProductId(ProductId productId) {
    List<Offer> offers = this.offersByProductId.get(productId);

    if (offers == null) {
      return new ArrayList<>();
    }

    return offers;
  }

  @Override
  public void clear() {
    this.offersByProductId = new HashMap<>();
  }
}