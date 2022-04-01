package ulaval.glo2003.product.domain.factory;


import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.OffersInformation;
import ulaval.glo2003.product.domain.OffersSummary;

import java.util.List;

public class OffersInformationFactory {

  public OffersInformation create(List<Offer> offers, OffersSummary offersSummary) {
    return new OffersInformation(
            offersSummary,
            offers
    );
  }
}
