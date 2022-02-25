package ulaval.glo2003.product.api.response;

import java.util.List;

public class ProductsResponse {
  public List<ProductResponse> products;

  public ProductsResponse(List<ProductResponse> products) {
    this.products = products;
  }

  public ProductsResponse() {

  };
}
