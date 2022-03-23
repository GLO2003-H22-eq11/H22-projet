package ulaval.glo2003.seller.api;

import ulaval.glo2003.product.api.ProductWithOfferResponse;
import ulaval.glo2003.product.api.assembler.ProductAssembler;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerWithProducts;

import java.util.List;
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


  public SellerWithOfferResponse toSellerWithOfferResponse(SellerWithProducts sellerInformation) {
    List<ProductWithOfferResponse> productsWithOffersResponse = this.productAssembler.
            toProductsWithOffersResponse(sellerInformation);

    return new SellerWithOfferResponse(sellerInformation.getStringSellerId(),
            sellerInformation.getName(), sellerInformation.getStringCreatedAt(), sellerInformation.getBio(),
            sellerInformation.getBirthDate(), productsWithOffersResponse);
  }


}
