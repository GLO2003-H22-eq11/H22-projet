package ulaval.glo2003.seller.domain;

import ulaval.glo2003.util.UUIDGenerator;

import java.util.UUID;

public class SellerIdentifier {
  private final UUID id;

  public SellerIdentifier() {
    this.id = UUIDGenerator.generate();
  }

  public String toString() {
    return this.id.toString();
  }
}
