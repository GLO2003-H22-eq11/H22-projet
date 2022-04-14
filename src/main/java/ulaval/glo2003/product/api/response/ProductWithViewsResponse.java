package ulaval.glo2003.product.api.response;

import ulaval.glo2003.offer.api.response.OffersSummaryResponse;

import java.util.List;

public class ProductWithViewsResponse {
  public String id;
  public String createdAt;
  public String title;
  public String description;
  public Double suggestedPrice;
  public OffersSummaryResponse offers;
  public List<String> categories;
  public Integer views;

  public ProductWithViewsResponse(
          String id,
          String createdAt,
          String title,
          String description,
          Double suggestedPrice,
          OffersSummaryResponse offers,
          List<String> categories,
          Integer views
  ) {
    this.id = id;
    this.createdAt = createdAt;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.offers = offers;
    this.categories = categories;
    this.views = views;
  }
}
