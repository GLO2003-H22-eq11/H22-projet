package ulaval.glo2003.offer.domain.factory;


import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.OffersInformation;
import ulaval.glo2003.offer.domain.OffersSummary;

import java.util.List;

public class OffersInformationFactory {

  public OffersInformation create(List<Offer> offers, OffersSummary offersSummary) {
    return new OffersInformation(
            offersSummary,
            offers
    );
  }
}
