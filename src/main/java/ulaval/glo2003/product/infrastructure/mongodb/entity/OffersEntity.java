package ulaval.glo2003.product.infrastructure.mongodb.entity;

import dev.morphia.annotations.Entity;

@Entity
public class OffersEntity {
  private Double mean;
  private Integer count;

  public OffersEntity() {
  }

  public OffersEntity(Double mean, Integer count) {
    this.mean = mean;
    this.count = count;
  }

  public Double getMean() {
    if (this.mean == null) {
      return null;
    }
    return this.mean;
  }

  public Integer getCount() {
    return this.count;
  }
}
