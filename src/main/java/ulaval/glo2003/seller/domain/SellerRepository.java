package ulaval.glo2003.seller.domain;

import ulaval.glo2003.seller.domain.exceptions.SellerNotFoundException;

public interface SellerRepository {
  void save(Seller seller);

  Seller findById(SellerId id) throws SellerNotFoundException;
}
