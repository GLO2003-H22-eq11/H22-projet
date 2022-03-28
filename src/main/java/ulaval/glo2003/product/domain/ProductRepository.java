package ulaval.glo2003.product.domain;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.List;


public interface ProductRepository {
  List<Product> findAll() throws GenericException;

  void save(Product product);

  Product findById(ProductId id) throws GenericException;

  List<Product> findBySellerId(SellerId sellerId) throws GenericException;

  void clear();
}
