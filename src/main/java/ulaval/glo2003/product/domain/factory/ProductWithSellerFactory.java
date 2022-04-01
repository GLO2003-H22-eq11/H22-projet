package ulaval.glo2003.product.domain.factory;

import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductWithSeller;
import ulaval.glo2003.seller.domain.Seller;

public class ProductWithSellerFactory {

  public ProductWithSeller createFrom(Product product, Seller seller) {
    return new ProductWithSeller(product, seller);
  }
}
