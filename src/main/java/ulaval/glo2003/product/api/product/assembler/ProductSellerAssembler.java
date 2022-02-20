package ulaval.glo2003.product.api.product.assembler;

import ulaval.glo2003.product.api.product.response.ProductSellerResponse;
import ulaval.glo2003.product.domain.product.ProductWithSeller;

public class ProductSellerAssembler {
  public ProductSellerResponse toResponse(ProductWithSeller productWithSeller) {
    return new ProductSellerResponse(
            productWithSeller.getSellerStringId(),
            productWithSeller.getSellerName()
    );
  }
}
