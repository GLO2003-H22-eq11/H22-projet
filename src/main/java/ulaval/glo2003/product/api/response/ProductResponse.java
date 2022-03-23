package ulaval.glo2003.product.api.response;

import java.util.List;
import java.util.Objects;

public class ProductResponse {
  public String id;
  public String createdAt;
  public String title;
  public String description;
  public Double suggestedPrice;
  public OffersResponse productOffers;
  public List<String> categories;
  public ProductSellerResponse seller;

  public ProductResponse() {
  };

  public ProductResponse(
          String id,
          String createdAt,
          String title,
          String description,
          Double suggestedPrice,
          OffersResponse productOffers,
          List<String> categories,
          ProductSellerResponse productSellerResponse) {
    this.id = id;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.productOffers = productOffers;
    this.categories = categories;
    this.seller = productSellerResponse;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductResponse that = (ProductResponse) o;
    return Objects.equals(suggestedPrice, that.suggestedPrice) && id.equals(that.id)
            && createdAt.equals(that.createdAt)
            && title.equals(that.title) && description.equals(that.description)
            && productOffers.equals(that.productOffers) && categories.equals(that.categories)

            && seller.equals(that.seller);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, title, description, suggestedPrice, productOffers, categories, seller);
  }
}
