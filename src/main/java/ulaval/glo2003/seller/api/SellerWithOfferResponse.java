package ulaval.glo2003.seller.api;

import ulaval.glo2003.product.api.ProductWithOfferResponse;

import java.util.List;

public class SellerWithOfferResponse {
  public String id;
  public String name;
  public String createdAt;
  public String bio;
  public String birthDate;
  public List<ProductWithOfferResponse> products;

  public SellerWithOfferResponse(String id, String name, String created, String bio, String birthDate,
                                 List<ProductWithOfferResponse> products) {
    this.id = id;
    this.name = name;
    this.createdAt = created;
    this.bio = bio;
    this.birthDate = birthDate;
    this.products = products;
  }
}
