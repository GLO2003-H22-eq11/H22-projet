package ulaval.glo2003.seller.infrastructure.inMemory;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerIdentifier;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.HashMap;

public class InMemorySellerRepository implements SellerRepository {
  private HashMap<SellerIdentifier, Seller> sellers = new HashMap<>();

  @Override
  public void save(Seller seller) {
    this.sellers.put(seller.getSellerIdentifier(), seller);
  }
}
