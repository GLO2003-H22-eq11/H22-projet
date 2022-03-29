package ulaval.glo2003.seller.api;

import ulaval.glo2003.product.api.assembler.ProductAssembler;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerWithProducts;

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

  public SellerWithProductsResponse toSellerWithProductsResponse(SellerWithProducts sellerWithProducts) {
    return new SellerWithProductsResponse(
            sellerWithProducts.getStringSellerId(),
            sellerWithProducts.getName(),
            sellerWithProducts.getStringCreatedAt(),
            sellerWithProducts.getBio(),
            sellerWithProducts.getBirthDate(),
            sellerWithProducts.getProducts().stream()
                    .map(this.productAssembler::toProductWithOffersResponse).collect(Collectors.toList())
    );
  }
}
