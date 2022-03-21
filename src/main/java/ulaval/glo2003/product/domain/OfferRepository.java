package ulaval.glo2003.product.domain;

public interface OfferRepository {

  void save(Offer offer, ProductId productId);
}
