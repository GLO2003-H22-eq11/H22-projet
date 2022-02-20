package ulaval.glo2003.product.domain.product;

import ulaval.glo2003.product.domain.Offers;
import ulaval.glo2003.seller.domain.Seller;

import java.util.List;


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

  public String getSellerStringId() {
    return this.seller.getStringId();
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

  public int getProductSuggestedPriceAmountIntValue() {
    return this.product.getSuggestedPriceAmountIntValue();
  }

  public Offers getProductOffers() {
    return this.product.getOffers();
  }

  public List<Category> getProductCategories() {
    return this.product.getProductCategories();
  }
}
