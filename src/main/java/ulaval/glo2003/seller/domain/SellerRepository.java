package ulaval.glo2003.seller.domain;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.exceptions.SellerNotFoundException;

public interface SellerRepository {
  void save(Seller seller);

  Seller findById(SellerId id) throws GenericException;

  void verifyIfSellerExists(SellerId id) throws SellerNotFoundException;

  void clear();
}
