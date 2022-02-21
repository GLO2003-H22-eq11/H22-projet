package ulaval.glo2003.product.api.assembler;

import ulaval.glo2003.product.api.response.OffersResponse;
import ulaval.glo2003.product.domain.Offers;

public class OffersAssembler {

  public OffersResponse toResponse(Offers offers) {
    return new OffersResponse(
            offers.getMeanAmount(),
            offers.getCount()
    );
  }
}
