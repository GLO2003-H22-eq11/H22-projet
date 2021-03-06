package ulaval.glo2003.product.domain.factory;

import ulaval.glo2003.offer.domain.factory.OffersInformationFactory;
import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductWithOffers;

import java.util.List;

public class ProductWithOffersFactory {
  private final OffersInformationFactory offersInformationFactory;

  public ProductWithOffersFactory(OffersInformationFactory offersInformationFactory) {
    this.offersInformationFactory = offersInformationFactory;
  }

  public ProductWithOffers create(Product product, List<Offer> offers) {
    return new ProductWithOffers(
            product,
            this.offersInformationFactory.create(offers, product.getOffersSummary())
    );
  }
}
