package ulaval.glo2003.product.api.response;

import java.util.Objects;

public class OffersResponse {
  public final Integer mean;
  public final Integer count;

  public OffersResponse(Integer mean, Integer count) {
    this.mean = mean;
    this.count = count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OffersResponse that = (OffersResponse) o;
    return mean.equals(that.mean) && count.equals(that.count);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mean, count);
  }
}
