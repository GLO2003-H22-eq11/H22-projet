package ulaval.glo2003.product.api.response;

import java.util.List;

public class ProductsWithSellerResponse {
  public List<ProductWithSellerResponse> products;

  public ProductsWithSellerResponse(List<ProductWithSellerResponse> products) {
    this.products = products;
  }

  public ProductsWithSellerResponse() {
  }
}
