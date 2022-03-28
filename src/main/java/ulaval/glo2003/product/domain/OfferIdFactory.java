package ulaval.glo2003.product.domain;

import ulaval.glo2003.product.domain.exceptions.InvalidOfferIdException;

public class OfferIdFactory {
  public OfferId create(String id) throws InvalidOfferIdException { return new OfferId(id); }

  public OfferId create() { return new OfferId(); }
}
