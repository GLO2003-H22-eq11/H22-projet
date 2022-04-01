package ulaval.glo2003.offer.api.assembler;

import ulaval.glo2003.offer.api.response.BuyerResponse;
import ulaval.glo2003.offer.domain.Buyer;

public class BuyerAssembler {

  public BuyerResponse toResponse(Buyer buyer) {
    return new BuyerResponse(
            buyer.getName(),
            buyer.getEmail(),
            buyer.getPhoneNumber()
    );
  }
}
