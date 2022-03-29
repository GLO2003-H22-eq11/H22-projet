package ulaval.glo2003.product.infrastructure.inMemory;

import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.domain.ProductRepository;
import ulaval.glo2003.product.domain.exceptions.ProductNotFoundException;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class InMemoryProductRepository implements ProductRepository {
  private HashMap<SellerId, List<Product>> productsBySellerId = new HashMap<>();
  private HashMap<ProductId, Product> productsByProductId = new HashMap<>();

  @Override
  public List<Product> findAll() {
    return new LinkedList<>(productsByProductId.values());
  }

  @Override
  public void save(Product product) {
    List<Product> products = productsBySellerId.get(product.getSellerId());
    if (products == null || !products.contains(product)) {
      this.productsBySellerId.computeIfAbsent(product.getSellerId(), k -> new ArrayList<>()).add(product);
    }
    this.productsByProductId.put(product.getProductId(), product);
  }

  @Override
  public Product findById(ProductId id) throws ProductNotFoundException {
    Product product = this.productsByProductId.get(id);
    if (product == null) {
      throw new ProductNotFoundException();
    }
    return product;
  }

  @Override
  public List<Product> findBySellerId(SellerId sellerId) {
    List<Product> products = this.productsBySellerId.get(sellerId);
    if (products == null) {
      return Collections.emptyList();
    }
    return products;
  }

  @Override
  public void clear() {
    this.productsBySellerId = new HashMap<>();
    this.productsByProductId = new HashMap<>();
  }
}
