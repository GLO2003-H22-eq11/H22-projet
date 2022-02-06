package ulaval.glo2003.products.infrastructure.inMemory;

import ulaval.glo2003.products.domain.Product;
import ulaval.glo2003.products.domain.ProductId;
import ulaval.glo2003.products.domain.ProductRepository;

import java.util.HashMap;


public class InMemoryProductRepository implements ProductRepository {
  private final HashMap<ProductId, Product> products = new HashMap<>();

  @Override
  public void save(Product product) {
    this.products.put(product.getProductId(), product);
  }
}
