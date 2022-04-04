package ulaval.glo2003.offer.domain;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.ProductId;

import java.util.List;

public interface OfferRepository {

  void save(Offer offer);

  List<Offer> findByProductId(ProductId productId) throws GenericException;

  void clear();
}
