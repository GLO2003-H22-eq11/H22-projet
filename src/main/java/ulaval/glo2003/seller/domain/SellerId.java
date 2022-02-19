package ulaval.glo2003.seller.domain;

import ulaval.glo2003.util.UUIDGenerator;

import java.util.Objects;

public class SellerId {
  private final String id;

  public SellerId() {
    this.id = UUIDGenerator.generate().toString();
  }

  public SellerId(String sellerId) {
    this.id = sellerId;
  }

  public String toString() {
    return this.id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SellerId sellerId = (SellerId) o;
    return id.equals(sellerId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
