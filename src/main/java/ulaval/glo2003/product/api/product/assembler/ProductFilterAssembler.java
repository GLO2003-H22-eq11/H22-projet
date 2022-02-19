package ulaval.glo2003.product.api.product.assembler;

import ulaval.glo2003.product.api.offers.OffersAssembler;
import ulaval.glo2003.product.api.product.ProductFilterRequest;
import ulaval.glo2003.product.api.product.response.ProductResponse;
import ulaval.glo2003.product.api.product.response.ProductSellerResponse;
import ulaval.glo2003.product.api.product.response.ProductsFilterResponse;
import ulaval.glo2003.product.domain.product.productCategories.ProductCategory;
import ulaval.glo2003.product.domain.product.productWithSeller.ProductWithSeller;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductFilterAssembler {

  private final OffersAssembler offersAssembler;

  public ProductFilterAssembler(OffersAssembler offersAssembler) {
    this.offersAssembler = offersAssembler;
  }

  public ProductFilterRequest toRequest(String sellerId, String title, List<String> categories,
                                        int minPrice, int maxPrice) {
    return new ProductFilterRequest(sellerId, title, categories, minPrice, maxPrice);
  }

  public ProductsFilterResponse toProductsResponse(List<ProductWithSeller> products) {
    List<ProductResponse> productResponses = new LinkedList<>();

    for (ProductWithSeller productWithSeller : products) {
      productResponses.add(this.toProductResponseFromProductWithSeller(productWithSeller));
    }

    return new ProductsFilterResponse(productResponses);
  }

  private ProductResponse toProductResponseFromProductWithSeller(ProductWithSeller productWithSeller) {
    ProductResponse productResponse = new ProductResponse(
            productWithSeller.getProductIdString(),
            productWithSeller.getCreatedAt(),
            productWithSeller.getTitle(),
            productWithSeller.getDescription(),
            productWithSeller.getSuggestedPrice(),
            this.offersAssembler.toResponse(productWithSeller.getOffers()),
            productWithSeller.getCategories().getProductCategories().stream().map(ProductCategory::getCategoryName)
                    .collect(Collectors.toList()),
            new ProductSellerResponse(productWithSeller.getStringSellerId(), productWithSeller.getSellerName())
    );

    return productResponse;
  }
}
