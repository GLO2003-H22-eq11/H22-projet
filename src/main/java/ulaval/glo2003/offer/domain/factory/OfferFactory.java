package ulaval.glo2003.offer.domain.factory;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.offer.domain.Buyer;
import ulaval.glo2003.offer.domain.Email;
import ulaval.glo2003.product.domain.factory.ProductIdFactory;
import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.PhoneNumber;
import ulaval.glo2003.offer.domain.exceptions.InvalidOfferNameException;
import ulaval.glo2003.offer.domain.exceptions.InvalidMessageException;

import java.time.Instant;

public class OfferFactory {
  private static final int MIN_MESSAGE_LENGTH = 100;

  private final ProductIdFactory productIdFactory;
  private final OfferIdFactory offerIdFactory;


  public OfferFactory(ProductIdFactory productIdFactory, OfferIdFactory offerIdFactory) {
    this.productIdFactory = productIdFactory;
    this.offerIdFactory = offerIdFactory;
  }

  public Offer create(
          String name,
          String email,
          String phoneNumber,
          Double amount,
          String message,
          String productId
  ) throws GenericException {

    if (name.isBlank()) {
      throw new InvalidOfferNameException();
    }

    if (message.length() < MIN_MESSAGE_LENGTH) {
      throw new InvalidMessageException();
    }

    return new Offer(
            this.offerIdFactory.create(),
            this.productIdFactory.create(productId),
            Amount.fromDouble(amount),
            message,
            Instant.now(),
            new Buyer(name, new Email(email), new PhoneNumber(phoneNumber))
    );
  }
}
