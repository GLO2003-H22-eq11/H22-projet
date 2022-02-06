package ulaval.glo2003.product.domain;

import ulaval.glo2003.product.domain.exceptions.ProductNotFoundException;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.List;


public interface ProductRepository {
  void save(Product product);

  List<Product> findBySellerId(SellerId sellerId);

  Product findByProductId(ProductId productId) throws ProductNotFoundException;
}
