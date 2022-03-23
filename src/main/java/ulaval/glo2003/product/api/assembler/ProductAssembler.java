package ulaval.glo2003.product.api.assembler;

import ulaval.glo2003.product.api.ProductWithOfferResponse;
import ulaval.glo2003.product.api.response.OffersInformationResponse;
import ulaval.glo2003.product.api.response.ProductResponse;
import ulaval.glo2003.product.api.response.ProductSellerResponse;
import ulaval.glo2003.product.api.response.ProductsResponse;
import ulaval.glo2003.product.domain.Category;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.ProductWithOffers;
import ulaval.glo2003.product.domain.ProductWithSeller;
import ulaval.glo2003.seller.api.SellerProductResponse;
import ulaval.glo2003.seller.domain.SellerWithProducts;

import java.util.ArrayList;
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
            this.offersAssembler.toResponse(product.getOffers()),
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

  public List<ProductWithOfferResponse> toProductsWithOffersResponse(SellerWithProducts sellerInformation) {
    List<ProductWithOfferResponse> productsWithOffersResponse = new ArrayList<>();

    for (ProductWithOffers productWithOffers : sellerInformation.getProducts()) {

      OffersInformationResponse offersInformationResponse =
              this.offersAssembler.toOffersInformationResponse(productWithOffers);

      ProductWithOfferResponse productWithOfferResponse = new ProductWithOfferResponse(
              productWithOffers.getStringId(),
              productWithOffers.getTitle(),
              productWithOffers.getDescription(),
              productWithOffers.getSuggestedPriceAmount(),
              productWithOffers.getProductCategories().stream().
                      map(Category::getCategoryName).collect(Collectors.toList()),
              offersInformationResponse);

      productsWithOffersResponse.add(productWithOfferResponse);
    }
    return productsWithOffersResponse;
  }
}
