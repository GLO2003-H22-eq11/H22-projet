package ulaval.glo2003.seller.domain;

import ulaval.glo2003.main.domain.exception.InvalidIdentifierException;

public class SellerIdFactory {

  public SellerId create(String id) throws InvalidIdentifierException {
    return new SellerId(id);
  }
}
