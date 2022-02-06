package ulaval.glo2003.product.domain;

import ulaval.glo2003.util.UUIDGenerator;

import java.util.UUID;

public class ProductId {
  private final UUID id;

  public ProductId() {
    this.id = UUIDGenerator.generate();
  }

  public String toString() {
    return this.id.toString();
  }
}
