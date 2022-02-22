package ulaval.glo2003.product.api.response;

import java.util.Objects;

public class ProductSellerResponse {
  public String id;
  public String name;

  public ProductSellerResponse(String id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductSellerResponse that = (ProductSellerResponse) o;
    return id.equals(that.id) && name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
