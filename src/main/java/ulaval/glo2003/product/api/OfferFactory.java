package ulaval.glo2003.product.api;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.main.domain.Email;
import ulaval.glo2003.main.domain.PhoneNumber;
import ulaval.glo2003.product.api.request.OfferRequest;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.ProductIdFactory;

public class OfferFactory {
  private final ProductIdFactory productIdFactory;

  public OfferFactory(ProductIdFactory productIdFactory) {
    this.productIdFactory = productIdFactory;
  }

  public Offer create(OfferRequest offerRequest, String productId) throws GenericException {
    return new Offer(
            this.productIdFactory.create(productId),
            offerRequest.name,
            new Email(offerRequest.email),
            new PhoneNumber(offerRequest.phoneNumber),
            Amount.fromDouble(offerRequest.amount),
            offerRequest.message
    );
  }
}
