package ulaval.glo2003.product.domain.product;

import ulaval.glo2003.util.UUIDGenerator;

import java.util.Objects;
import java.util.UUID;

public class ProductId {
  private final UUID id;

  public ProductId() {
    this.id = UUIDGenerator.generate();
  }

  public ProductId(String productId) { this.id = UUID.fromString(productId); }

  public String toString() {
    return this.id.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductId productId = (ProductId) o;
    return id.equals(productId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
