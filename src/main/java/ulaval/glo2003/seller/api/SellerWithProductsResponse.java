package ulaval.glo2003.seller.api;

import ulaval.glo2003.product.api.ProductWithOffersResponse;

import java.util.List;

public class SellerWithProductsResponse {
  public String id;
  public String name;
  public String createdAt;
  public String bio;
  public String birthDate;
  public List<ProductWithOffersResponse> products;

  public SellerWithProductsResponse(){}

  public SellerWithProductsResponse(
          String id,
          String name,
          String created,
          String bio,
          String birthDate,
          List<ProductWithOffersResponse> products
  ) {
    this.id = id;
    this.name = name;
    this.createdAt = created;
    this.bio = bio;
    this.birthDate = birthDate;
    this.products = products;
  }
}
