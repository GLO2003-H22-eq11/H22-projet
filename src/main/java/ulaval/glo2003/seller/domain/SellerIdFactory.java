package ulaval.glo2003.seller.domain;

import ulaval.glo2003.seller.domain.exceptions.InvalidSellerIdException;

public class SellerIdFactory {

  public SellerId create(String id) throws InvalidSellerIdException {
    return new SellerId(id);
  }
}
