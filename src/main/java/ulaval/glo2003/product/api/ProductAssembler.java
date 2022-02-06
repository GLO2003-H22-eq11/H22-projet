package ulaval.glo2003.product.api;

import ulaval.glo2003.product.domain.Product;

public class ProductAssembler {
  private final OffersAssembler offersAssembler;

  public ProductAssembler(OffersAssembler offersAssembler) {
    this.offersAssembler = offersAssembler;
  }

  public ProductResponse toResponse(Product product) {
    return new ProductResponse(
            product.getStringProductId(),
            product.getStringCreatedAt(),
            product.getTitle(),
            product.getDescription(),
            product.getSuggestedPriceAmount(),
            this.offersAssembler.toResponse(product.getOffers())
    );
  }
}
