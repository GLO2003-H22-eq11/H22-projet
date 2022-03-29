package ulaval.glo2003.product.domain;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.List;
import java.util.Objects;


public class ProductWithSeller {
  private final Product product;
  private final Seller seller;

  public ProductWithSeller(Product product, Seller seller) {
    this.product = product;
    this.seller = seller;
  }

  public String getProductStringId() {
    return this.product.getProductId().toString();
  }

  public String getSellerName() {
    return this.seller.getName();
  }

  public String getProductStringCreatedAt() {
    return this.product.getStringCreatedAt();
  }

  public String getProductTitle() {
    return this.product.getTitle();
  }

  public String getProductDescription() {
    return this.product.getDescription();
  }

  public Double getProductSuggestedPriceAmountIntValue() {
    return this.product.getSuggestedPriceAmountDoubleValue();
  }

  public OffersSummary getProductOffers() {
    return this.product.getOffersSummary();
  }

  public List<Category> getProductCategories() {
    return this.product.getProductCategories();
  }

  public SellerId getSellerId() {
    return this.seller.getSellerId();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductWithSeller that = (ProductWithSeller) o;
    return product.equals(that.product) && seller.equals(that.seller);
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, seller);
  }
}
