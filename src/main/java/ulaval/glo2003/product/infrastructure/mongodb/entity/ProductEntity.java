package ulaval.glo2003.product.infrastructure.mongodb.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("Products")
public class ProductEntity {

  @Id
  public String productId;
}
