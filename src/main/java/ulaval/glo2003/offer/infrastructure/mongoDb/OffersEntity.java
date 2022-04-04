package ulaval.glo2003.offer.infrastructure.mongoDb;

import dev.morphia.annotations.Entity;

@Entity("Offers")
public class OffersEntity {
  private Double mean;
  private Integer count;
  private Double min;
  private Double max;

  public OffersEntity() {
  }

  public OffersEntity(Double mean, Integer count, Double min, Double max) {
    this.mean = mean;
    this.count = count;
    this.min = min;
    this.max = max;
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

  public Double getMin() {
    return this.min;
  }

  public Double getMax() {
    return this.max;
  }
}
