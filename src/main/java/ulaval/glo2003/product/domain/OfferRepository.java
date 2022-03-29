package ulaval.glo2003.product.domain;

import java.util.List;

public interface OfferRepository {

  void save(Offer offer);

  List<Offer> findByProductId(ProductId productId);
}
