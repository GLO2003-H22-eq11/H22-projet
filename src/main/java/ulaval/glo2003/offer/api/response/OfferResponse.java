package ulaval.glo2003.offer.api.response;

public class OfferResponse {
  public String id;
  public Double amount;
  public String message;
  public BuyerResponse buyer;
  public String createdAt;

  public OfferResponse() {
  }

  public OfferResponse(String id, Double amount, String message, BuyerResponse buyer, String createdAt) {
    this.id = id;
    this.amount = amount;
    this.message = message;
    this.buyer = buyer;
    this.createdAt = createdAt;
  }
}
