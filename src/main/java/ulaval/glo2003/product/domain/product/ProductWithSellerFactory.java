package ulaval.glo2003.product.domain.product;

import ulaval.glo2003.seller.domain.Seller;

public class ProductWithSellerFactory {

  public ProductWithSeller createFrom(Product product, Seller seller) {
    return new ProductWithSeller(product, seller);
  }
}
