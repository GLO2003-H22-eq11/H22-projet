package ulaval.glo2003.product.domain.factory;


import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.domain.exceptions.InvalidProductIdException;

public class ProductIdFactory {

  public ProductId create(String id) throws InvalidProductIdException { return new ProductId(id); }

  public ProductId create() { return new ProductId(); }
}
