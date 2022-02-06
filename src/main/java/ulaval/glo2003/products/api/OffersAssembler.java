package ulaval.glo2003.products.api;

import ulaval.glo2003.products.domain.Offers;

public class OffersAssembler {

  public OffersResponse toResponse(Offers offers) {
    return new OffersResponse(
            offers.getMeanAmount(),
            offers.getCount()
    );
  }
}
