package ulaval.glo2003.products.infrastructure.inMemory;

import ulaval.glo2003.products.domain.Product;
import ulaval.glo2003.products.domain.ProductRepository;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.HashMap;

public class InMemoryProductRepository implements ProductRepository {
  private final HashMap<SellerId, Product> products = new HashMap<>();

  @Override
  public void save(Product product) {
    this.products.put(product.getSellerId(), product);
  }
}
