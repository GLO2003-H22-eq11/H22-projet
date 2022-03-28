package ulaval.glo2003.product.infrastructure.inMemory;

import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.OfferRepository;
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
  public void clear() {
    this.offersByProductId = new HashMap<>();
  }
}
