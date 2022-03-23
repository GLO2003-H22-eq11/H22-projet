package ulaval.glo2003.product.api.response;

public class OfferResponse {
  public String id;
  public double amount;
  public String message;
  public BuyerResponse buyer;

  public OfferResponse(String id, double amount, String message, BuyerResponse buyer) {
    this.id = id;
    this.amount = amount;
    this.message = message;
    this.buyer = buyer;
  }
}
