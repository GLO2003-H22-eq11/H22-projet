package ulaval.glo2003.product.domain.product;

import ulaval.glo2003.product.domain.exceptions.ProductNotFoundException;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.List;


public interface ProductRepository {
  List<Product> findAll();

  void save(Product product);

  Product findById(ProductId id) throws ProductNotFoundException;

  List<Product> findBySellerId(SellerId sellerId);
}
