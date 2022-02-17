package ulaval.glo2003.product.infrastructure.inMemory;

import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.exceptions.ProductNotFoundException;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class InMemoryProductRepository implements ProductRepository {
  private final HashMap<SellerId, List<Product>> productsBySellerId = new HashMap<>();
  private final HashMap<ProductId, Product> productsByProductId = new HashMap<>();

  @Override
  public void save(Product product) {
    this.productsBySellerId.computeIfAbsent(product.getSellerId(), k -> new ArrayList<>()).add(product);
    this.productsByProductId.put(product.getProductId(), product);
  }

  @Override
  public Product findById(ProductId id) throws ProductNotFoundException {
    return null;
  }

  @Override
  public List<Product> findBySellerId(SellerId sellerId) {
    List<Product> products = this.productsBySellerId.get(sellerId);
    if (products == null) {
      return Collections.emptyList();
    }
    return products;
  }
}
