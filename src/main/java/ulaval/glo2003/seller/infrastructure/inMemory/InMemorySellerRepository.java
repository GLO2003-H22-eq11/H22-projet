package ulaval.glo2003.seller.infrastructure.inMemory;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;

import java.util.LinkedList;
import java.util.List;

public class InMemorySellerRepository implements SellerRepository {
  private List<Seller> sellers = new LinkedList<>();

  @Override
  public void save(Seller seller) {
    this.sellers.add(seller);
  }
}
