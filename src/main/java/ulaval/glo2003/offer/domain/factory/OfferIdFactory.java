package ulaval.glo2003.offer.domain.factory;

import ulaval.glo2003.offer.domain.OfferId;
import ulaval.glo2003.offer.domain.exceptions.InvalidOfferIdException;

public class OfferIdFactory {
  public OfferId create(String id) throws InvalidOfferIdException { return new OfferId(id); }

  public OfferId create() { return new OfferId(); }
}
