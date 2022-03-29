package ulaval.glo2003.product.domain;

import ulaval.glo2003.product.domain.exceptions.InvalidOfferIdException;
import ulaval.glo2003.util.UUIDGenerator;

import java.util.Objects;
import java.util.UUID;

public class OfferId {
  private final UUID id;

  public OfferId() {
    this.id = UUIDGenerator.generate();
  }

  public OfferId(String offerId) throws InvalidOfferIdException {
    try {
      this.id = UUID.fromString(offerId);
    } catch (Exception e) {
      throw new InvalidOfferIdException();
    }
  }

  public String toString() {
    return this.id.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OfferId offerId = (OfferId) o;
    return id.equals(offerId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
