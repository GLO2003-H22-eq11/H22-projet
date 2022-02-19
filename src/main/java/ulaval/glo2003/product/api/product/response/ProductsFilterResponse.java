package ulaval.glo2003.product.api.product.response;

import java.util.List;

public class ProductsFilterResponse {
  public  List<ProductResponse> products;

  public ProductsFilterResponse(List<ProductResponse> products) {
    this.products = products;
  };
}
