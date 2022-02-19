package ulaval.glo2003.product.api.product.assembler;

import ulaval.glo2003.product.api.offers.OffersAssembler;
import ulaval.glo2003.product.api.product.response.ProductResponse;
import ulaval.glo2003.product.api.product.response.ProductSellerResponse;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.productCategories.ProductCategory;
import ulaval.glo2003.seller.api.SellerProductResponse;
import ulaval.glo2003.seller.domain.Seller;


import java.util.stream.Collectors;

public class ProductAssembler {
  private final OffersAssembler offersAssembler;

  public ProductAssembler(OffersAssembler offersAssembler) {
    this.offersAssembler = offersAssembler;
  }

  public SellerProductResponse toSellerProductResponse(Product product) {
    return new SellerProductResponse(
            product.getStringProductId(),
            product.getStringCreatedAt(),
            product.getTitle(),
            product.getDescription(),
            product.getSuggestedPriceAmount(),
            this.offersAssembler.toResponse(product.getOffers()));

  }

  public ProductResponse toResponse(Product product, Seller seller) {
    return new ProductResponse(
            product.getStringProductId(),
            product.getStringCreatedAt(),
            product.getTitle(),
            product.getDescription(),
            product.getSuggestedPriceAmount(),
            this.offersAssembler.toResponse(product.getOffers()),
            product.getProductCategories().stream().map(ProductCategory::getCategoryName).collect(Collectors.toList()),
            new ProductSellerResponse(seller.getStringSellerId(), seller.getName()));
  }
}
