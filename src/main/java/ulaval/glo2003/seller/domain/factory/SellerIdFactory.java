package ulaval.glo2003.seller.domain.factory;

import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.exceptions.InvalidSellerIdException;

public class SellerIdFactory {

  public SellerId create() {
    return new SellerId();
  }

  public SellerId create(String id) throws InvalidSellerIdException {
    return new SellerId(id);
  }
}
