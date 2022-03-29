package ulaval.glo2003.seller.domain;

import ulaval.glo2003.product.domain.ProductWithOffers;

import java.util.List;
import java.util.Objects;

public class SellerWithProducts {

  private final Seller seller;
  private final List<ProductWithOffers> products;

  public SellerWithProducts(
          Seller seller,
          List<ProductWithOffers> products
  ) {
    this.seller = seller;
    this.products = products;
  }

  public Seller getSeller() {
    return this.seller;
  }

  public List<ProductWithOffers> getProducts() {
    return products;
  }

  public String getStringSellerId() {
    return this.seller.getStringSellerId();
  }

  public String getName() {
    return this.seller.getName();
  }

  public String getStringCreatedAt() {
    return this.seller.getStringCreatedAt();
  }

  public String getBio() {
    return this.seller.getBio();
  }

  public String getBirthDate() {
    return this.seller.getStringBirthDate();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SellerWithProducts that = (SellerWithProducts) o;
    return seller.equals(that.seller) && products.equals(that.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(seller, products);
  }
}
