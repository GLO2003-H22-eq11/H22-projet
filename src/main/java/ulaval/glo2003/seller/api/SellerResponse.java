package ulaval.glo2003.seller.api;

import ulaval.glo2003.product.api.ProductResponse;

import java.util.Objects;
import java.util.List;

public class SellerResponse {
  public final String id;
  public final String name;
  public final String createdAt;
  public final String bio;
  public final List<ProductResponse> products;

  public SellerResponse(String id, String name, String createdAt, String bio, List<ProductResponse> products) {
    this.id = id;
    this.name = name;
    this.createdAt = createdAt;
    this.bio = bio;
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SellerResponse that = (SellerResponse) o;
    return id.equals(that.id) && name.equals(that.name) && createdAt.equals(that.createdAt) && bio.equals(that.bio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, createdAt, bio);
  }
}
