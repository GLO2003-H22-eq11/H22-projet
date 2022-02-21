package ulaval.glo2003.seller.infrastructure.inMemory;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.domain.exceptions.SellerNotFoundException;

import java.util.HashMap;

public class InMemorySellerRepository implements SellerRepository {
  private final HashMap<SellerId, Seller> sellers = new HashMap<>();

  @Override
  public void save(Seller seller) {
    this.sellers.put(seller.getSellerId(), seller);
  }

  @Override
  public Seller findById(SellerId id) throws SellerNotFoundException {
    Seller seller = this.sellers.get(id);
    if (seller == null) {
      throw new SellerNotFoundException();
    }
    return seller;
  }

  @Override
  public void verifyIfSellerExists(SellerId id) throws SellerNotFoundException {
    if (this.sellers.get(id) == null) {
      throw new SellerNotFoundException();
    }
  }
}
