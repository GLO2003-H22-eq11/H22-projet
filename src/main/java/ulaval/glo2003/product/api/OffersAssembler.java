package ulaval.glo2003.product.api;

import ulaval.glo2003.product.domain.Offers;

public class OffersAssembler {

  public OffersResponse toResponse(Offers offers) {
    return new OffersResponse(
            null,
            offers.getCount()
    );
  }
}
