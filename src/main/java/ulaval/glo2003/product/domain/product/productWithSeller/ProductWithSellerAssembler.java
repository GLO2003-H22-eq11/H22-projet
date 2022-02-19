package ulaval.glo2003.product.domain.product.productWithSeller;


import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.seller.domain.Seller;


public class ProductWithSellerAssembler {

  public ProductWithSeller toProductWithSeller(Product product, Seller seller) {
    return new ProductWithSeller(
            product.getProductId(),
            product.getStringCreatedAt(),
            product.getTitle(),
            product.getDescription(),
            product.getSuggestedPriceAmount(),
            product.getOffers(),
            product.getCategories(),
            seller
    );
  }
}
