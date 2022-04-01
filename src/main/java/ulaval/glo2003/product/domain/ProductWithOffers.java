package ulaval.glo2003.product.domain;


import ulaval.glo2003.offer.domain.OffersInformation;

import java.util.List;
import java.util.Objects;

public class ProductWithOffers {

  private final Product product;
  private final OffersInformation offersInformation;

  public ProductWithOffers(
          Product product,
          OffersInformation offersInformation
  ) {
    this.product = product;
    this.offersInformation = offersInformation;
  }

  public Product getProduct() {
    return this.product;
  }

  public OffersInformation getOffers() {
    return this.offersInformation;
  }

  public String getStringProductId() {
    return this.product.getStringProductId();
  }

  public String getProductTitle() {
    return this.product.getTitle();
  }

  public String getProductDescription() {
    return this.product.getDescription();
  }

  public Double getProductSuggestedPriceAmount() {
    return this.product.getSuggestedPriceAmountDoubleValue();
  }

  public List<Category> getProductCategories() {
    return this.product.getProductCategories();
  }

  public String getProductCreatedAt() {
    return this.product.getStringCreatedAt();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductWithOffers that = (ProductWithOffers) o;
    return product.equals(that.product) && offersInformation.equals(that.offersInformation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, offersInformation);
  }
}
