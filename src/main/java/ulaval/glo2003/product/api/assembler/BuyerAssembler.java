package ulaval.glo2003.product.api.assembler;

import ulaval.glo2003.product.api.response.BuyerResponse;
import ulaval.glo2003.product.domain.Buyer;

public class BuyerAssembler {

  public BuyerResponse toResponse(Buyer buyer) {
    return new BuyerResponse(
            buyer.getName(),
            buyer.getEmail(),
            buyer.getPhoneNumber()
    );
  }
}
