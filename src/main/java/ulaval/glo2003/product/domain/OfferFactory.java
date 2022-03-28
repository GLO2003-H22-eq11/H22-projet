package ulaval.glo2003.product.domain;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.exceptions.InvalidOfferNameException;
import ulaval.glo2003.product.domain.exceptions.InvalidMessageException;

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
            name,
            new Email(email),
            new PhoneNumber(phoneNumber),
            Amount.fromDouble(amount),
            message
    );
  }
}
