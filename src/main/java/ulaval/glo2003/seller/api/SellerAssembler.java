package ulaval.glo2003.seller.api;

import ulaval.glo2003.product.api.product.assembler.ProductAssembler;
import ulaval.glo2003.seller.domain.Seller;

import java.util.stream.Collectors;

public class SellerAssembler {
  private final ProductAssembler productAssembler;

  public SellerAssembler(ProductAssembler productAssembler) {
    this.productAssembler = productAssembler;
  }

  public SellerResponse toResponse(Seller seller) {
    return new SellerResponse(
            seller.getStringSellerId(),
            seller.getName(),
            seller.getStringCreatedAt(),
            seller.getBio(),
            seller.getProducts().stream().map(this.productAssembler::toSellerProductResponse)
                    .collect(Collectors.toList())
    );
  }
}
