package ulaval.glo2003.product.api.product;

import ulaval.glo2003.product.api.offers.OffersAssembler;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.productCategories.ProductCategory;
import ulaval.glo2003.product.domain.product.productWithSeller.ProductWithSeller;
import ulaval.glo2003.seller.api.SellerProductResponse;
import ulaval.glo2003.seller.domain.Seller;

import java.util.LinkedList;
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

  public ProductsResponse toProductsResponse(List<ProductWithSeller> products) {
    List<ProductResponse> productResponses = new LinkedList<>();

    for (ProductWithSeller productWithSeller : products) {
      ProductResponse productResponse = this.toProductResponseFromProductWithSeller(productWithSeller);
      productResponses.add(productResponse);
    }

    ProductsResponse productsResponse =  new ProductsResponse();
    productsResponse.products = productResponses;
    return productsResponse;
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
