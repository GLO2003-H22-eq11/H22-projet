package ulaval.glo2003.product.domain;


import java.util.List;

public class ProductWithOffers {

  private final ProductId productId;
  private final String title;
  private final String description;
  private final Amount suggestedPrice;
  private final Categories categories;
  private final List<Offer> productOffers;
  private final OffersSummary offersSummary;

  public ProductWithOffers(
          ProductId productId,
          String title,
          String description,
          Amount suggestedPrice,
          Categories categories,
          List<Offer> productOffers,
          OffersSummary offersSummarySummary
  ) {
    this.productId = productId;
    this.title = title;
    this.description = description;
    this.suggestedPrice = suggestedPrice;
    this.productOffers = productOffers;
    this.categories = categories;
    this.offersSummary = offersSummarySummary;
  }

  public String getTitle() {
    return this.title;
  }

  public String getDescription() {
    return this.description;
  }

  public double getSuggestedPriceAmount() {
    return this.suggestedPrice.getDoubleValue();
  }

  public String getStringId() {
    return this.productId.toString();
  }

  public ProductId getProductId() {
    return this.productId;
  }

  public List<Category> getProductCategories() {
    return this.categories.getCategories();
  }

  public Double getMean() {
    Double mean = this.offersSummary.getMeanAmount();

    if (mean == null) {
      return null;
    }

    return mean;
  }

  public int getCount() {
    return this.offersSummary.getCount();
  }

  public double getMaxOffer() {
    return this.offersSummary.getMax().getDoubleValue();
  }

  public double getMinOffer() {
    return this.offersSummary.getMin().getDoubleValue();
  }

  public List<Offer> getProductOffers() {
    return this.productOffers;
  }
}
