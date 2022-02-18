package ulaval.glo2003.product.api;

import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.seller.api.SellerProductResponse;
import ulaval.glo2003.seller.domain.Seller;

public class ProductAssembler {
  private final OffersAssembler offersAssembler;

  public ProductAssembler(OffersAssembler offersAssembler) {
    this.offersAssembler = offersAssembler;
  }

  public SellerProductResponse toResponse(Product product) {
    return new SellerProductResponse(
            product.getStringProductId(),
            product.getStringCreatedAt(),
            product.getTitle(),
            product.getDescription(),
            product.getSuggestedPriceAmount(),
            this.offersAssembler.toResponse(product.getOffers()));

  }
  public ProductResponse toProductResponse(Product product, Seller seller) {
    return new ProductResponse(
            product.getStringProductId(),
            product.getStringCreatedAt(),
            product.getTitle(),
            product.getDescription(),
            product.getSuggestedPriceAmount(),
            this.offersAssembler.toResponse(product.getOffers()),
            product.getCategories(),
            new ProductSellerResponse(seller.getStringSellerId(), seller.getName()));
  }
}
