package ulaval.glo2003.product.api.response;

import java.util.List;
import java.util.Objects;

public class ProductResponse {
  public final String id;
  public final String createdAt;
  public final String title;
  public final String description;
  public final int suggestedPrice;
  public final OffersResponse offers;
  public final List<String> categories;
  public final ProductSellerResponse seller;

  public ProductResponse(
          String id,
          String createdAt,
          String title,
          String description,
          int suggestedPrice,
          OffersResponse offers,
          List<String> categories,
          ProductSellerResponse productSellerResponse) {
    this.id = id;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
    this.categories = categories;
    this.seller = productSellerResponse;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductResponse that = (ProductResponse) o;
    return suggestedPrice == that.suggestedPrice && id.equals(that.id) && createdAt.equals(that.createdAt)
            && title.equals(that.title) && description.equals(that.description)
            && offers.equals(that.offers) && categories.equals(that.categories) && seller.equals(that.seller);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, title, description, suggestedPrice, offers, categories, seller);
  }
}
