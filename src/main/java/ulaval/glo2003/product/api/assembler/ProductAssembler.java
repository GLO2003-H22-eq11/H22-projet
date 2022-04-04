package ulaval.glo2003.product.api.assembler;

import ulaval.glo2003.offer.api.assembler.OffersAssembler;
import ulaval.glo2003.product.api.response.ProductWithOffersResponse;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.product.api.response.ProductSellerResponse;
import ulaval.glo2003.product.api.response.ProductsResponse;
import ulaval.glo2003.product.domain.Category;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductWithOffers;
import ulaval.glo2003.product.domain.ProductWithSeller;
import ulaval.glo2003.seller.api.response.SellerProductResponse;

import java.util.List;
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
            product.getSuggestedPriceAmountDoubleValue(),
            this.offersAssembler.toResponse(product.getOffersSummary()),
            product.getProductCategories().stream().
                    map(Category::getCategoryName).collect(Collectors.toList()));

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
            new ProductSellerResponse(productWithSeller.getSellerId().toString(), productWithSeller.getSellerName()));
  }

  public ProductsResponse toProductsResponse(List<ProductWithSeller> productsWithSellers) {
    return new ProductsResponse(
            productsWithSellers.stream().map(this::toResponse).collect(Collectors.toList())
    );
  }

  public ProductWithOffersResponse toProductWithOffersResponse(ProductWithOffers productWithOffers) {
    return new ProductWithOffersResponse(
            productWithOffers.getStringProductId(),
            productWithOffers.getProductTitle(),
            productWithOffers.getProductDescription(),
            productWithOffers.getProductSuggestedPriceAmount(),
            productWithOffers.getProductCategories().stream().
                    map(Category::getCategoryName).collect(Collectors.toList()),
            this.offersAssembler.toOffersInformationResponse(productWithOffers.getOffers()),
            productWithOffers.getProductCreatedAt());
  }
}
