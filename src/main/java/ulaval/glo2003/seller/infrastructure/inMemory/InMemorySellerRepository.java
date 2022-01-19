package ulaval.glo2003.seller.infrastructure.inMemory;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.HashMap;
import java.util.Optional;

public class InMemorySellerRepository implements SellerRepository {
  private final HashMap<SellerId, Seller> sellers = new HashMap<>();

  @Override
  public void save(Seller seller) {
    this.sellers.put(seller.getSellerId(), seller);
  }

  @Override
  public Optional<Seller> findById(SellerId id) {
    return Optional.of(this.sellers.get(id));
  }
}
