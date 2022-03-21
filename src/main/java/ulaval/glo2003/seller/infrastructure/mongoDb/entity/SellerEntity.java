package ulaval.glo2003.seller.infrastructure.mongoDb.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("Sellers")
public class SellerEntity {

  @Id
  public String sellerId;
}
