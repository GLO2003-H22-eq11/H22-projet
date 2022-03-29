package ulaval.glo2003.product.domain;


import java.util.List;

public class OffersInformationFactory {

  public OffersInformation create(List<Offer> offers, OffersSummary offersSummary) {
    return new OffersInformation(
            offersSummary,
            offers
    );
  }
}
