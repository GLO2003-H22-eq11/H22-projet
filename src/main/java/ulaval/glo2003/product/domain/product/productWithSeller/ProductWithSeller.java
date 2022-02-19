package ulaval.glo2003.product.domain.product.productWithSeller;


import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.product.domain.product.productCategories.Categories;
import ulaval.glo2003.product.domain.product.productId.ProductId;
import ulaval.glo2003.seller.domain.Seller;

public class ProductWithSeller {
  private final ProductId productId;
  private final String createdAt;
  private final String title;
  private final String description;
  private final int suggestedPrice;
  private final Offers offers;
  private final Categories categories;
  private final Seller seller;


  public ProductWithSeller(
          ProductId productId,
          String createdAt,
          String title,
          String description,
          int suggestedPrice,
          Offers offers,
          Categories categories,
          Seller seller) {
    this.productId = productId;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
    this.categories = categories;
    this.seller = seller;
  }

  public Categories getCategories() {
    return categories;
  }

  public String getTitle() {
    return title;
  }

  public int getSuggestedPrice() {
    return suggestedPrice;
  }

  public Offers getOffers() {
    return offers;
  }

  public ProductId getProductId() {
    return productId;
  }

  public String getProductIdString() {
    return this.productId.toString();
  }

  public String getSellerName() {
    return this.seller.getName();
  }

  public String getStringSellerId() {
    return this.seller.getStringSellerId();
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getDescription() {
    return description;
  }
}
