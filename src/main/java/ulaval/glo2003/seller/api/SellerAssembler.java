package ulaval.glo2003.seller.api;

import ulaval.glo2003.product.api.ProductWithOffersResponse;
import ulaval.glo2003.product.api.assembler.ProductAssembler;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerWithProductsOffers;

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


  public SellerWithProductOffersResponse toSellerWithProductsOffersResponse(SellerWithProductsOffers
                                                                                    sellerWithProductsOffers) {
    List<ProductWithOffersResponse> productsWithOffersResponse = this.productAssembler.
            toProductsWithOffersResponse(sellerWithProductsOffers.getProducts());

    return new SellerWithProductOffersResponse(
            sellerWithProductsOffers.getStringSellerId(),
            sellerWithProductsOffers.getName(),
            sellerWithProductsOffers.getStringCreatedAt(),
            sellerWithProductsOffers.getBio(),
            sellerWithProductsOffers.getBirthDate(),
            productsWithOffersResponse);
  }


}
