package ulaval.glo2003.product.api.assembler;

import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.main.domain.Email;
import ulaval.glo2003.main.domain.PhoneNumber;
import ulaval.glo2003.product.api.request.OfferRequest;
import ulaval.glo2003.product.domain.Offer;

public class OfferAssembler {
  public Offer toDomain(OfferRequest offerRequest) {
    return new Offer(
            offerRequest.name,
            new Email(offerRequest.email),
            new PhoneNumber(offerRequest.phoneNumber),
            Amount.fromInt(offerRequest.amount),
            offerRequest.message
    );
  }
}
