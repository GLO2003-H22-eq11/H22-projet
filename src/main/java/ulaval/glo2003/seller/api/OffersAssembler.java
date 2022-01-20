package ulaval.glo2003.seller.api;

import ulaval.glo2003.seller.domain.Offers;

public class OffersAssembler {

  public OffersResponse toResponse(Offers offers) {
    return new OffersResponse(
            offers.getMeanAmount(),
            offers.getCount()
    );
  }
}
