package ulaval.glo2003.seller.domain;

import ulaval.glo2003.product.domain.ProductWithOffers;

import java.util.List;

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
}
