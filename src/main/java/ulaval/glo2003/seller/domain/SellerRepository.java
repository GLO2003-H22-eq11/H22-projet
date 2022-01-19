package ulaval.glo2003.seller.domain;

import java.util.Optional;

public interface SellerRepository {
  void save(Seller seller);

  Optional<Seller> findById(SellerId id);

}
