package ulaval.glo2003.product.api.product.assembler;

import ulaval.glo2003.product.api.offers.OffersAssembler;
import ulaval.glo2003.product.api.product.response.ProductResponse;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.Category;
import ulaval.glo2003.product.domain.product.ProductWithSeller;
import ulaval.glo2003.seller.api.SellerProductResponse;


import java.util.stream.Collectors;

public class ProductAssembler {
  private final OffersAssembler offersAssembler;
  private final ProductSellerAssembler productSellerAssembler;

  public ProductAssembler(OffersAssembler offersAssembler, ProductSellerAssembler productSellerAssembler) {
    this.offersAssembler = offersAssembler;
    this.productSellerAssembler = productSellerAssembler;
  }

  public SellerProductResponse toSellerProductResponse(Product product) {
    return new SellerProductResponse(
            product.getStringProductId(),
            product.getStringCreatedAt(),
            product.getTitle(),
            product.getDescription(),
            product.getSuggestedPriceAmountIntValue(),
            this.offersAssembler.toResponse(product.getOffers()));

  }

  public ProductResponse toResponse(ProductWithSeller productWithSeller) {
    return new ProductResponse(
            productWithSeller.getProductStringId(),
            productWithSeller.getProductStringCreatedAt(),
            productWithSeller.getProductTitle(),
            productWithSeller.getProductDescription(),
            productWithSeller.getProductSuggestedPriceAmountIntValue(),
            this.offersAssembler.toResponse(productWithSeller.getProductOffers()),
            productWithSeller.getProductCategories().stream().
                    map(Category::getCategoryName).collect(Collectors.toList()),
            this.productSellerAssembler.toResponse(productWithSeller));
  }
}
