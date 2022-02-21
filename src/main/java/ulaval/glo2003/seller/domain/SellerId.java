package ulaval.glo2003.seller.domain;

import ulaval.glo2003.main.domain.exception.InvalidIdentifierException;
import ulaval.glo2003.util.UUIDGenerator;

import java.util.Objects;
import java.util.UUID;

public class SellerId {
  private final UUID id;

  public SellerId() {
    this.id = UUIDGenerator.generate();
  }

  public SellerId(String sellerId) throws InvalidIdentifierException {
    try {
      this.id = UUID.fromString(sellerId);
    } catch (Exception e) {
      throw new InvalidIdentifierException();
    }
  }

  public String toString() {
    return this.id.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SellerId sellerId = (SellerId) o;
    return id.toString().equals(sellerId.id.toString());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
