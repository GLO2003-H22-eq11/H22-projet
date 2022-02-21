package ulaval.glo2003.product.domain;


import ulaval.glo2003.main.domain.exception.InvalidIdentifierException;

public class ProductIdFactory {

  public ProductId create(String id) throws InvalidIdentifierException { return new ProductId(id); }

  public ProductId create() { return new ProductId(); }
}
