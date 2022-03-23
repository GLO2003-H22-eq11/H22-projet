package ulaval.glo2003.product.domain;

public class Offer {

  private final OfferId offerId;
  private final ProductId productId;
  private final String name;
  private final Email email;
  private final PhoneNumber phoneNumber;
  private final Amount amount;
  private final String message;

  public Offer(OfferId offerId, ProductId productId, String name, Email email, PhoneNumber phoneNumber,
               Amount amount, String message) {
    this.offerId = offerId;
    this.productId = productId;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.amount = amount;
    this.message = message;
  }

  public String getId() {
    return this.offerId.toString();
  }

  public ProductId getProductId() {
    return productId;
  }

  public Amount getAmount() {
    return this.amount;
  }

  public String getMessage() {
    return this.message;
  }

  public String getPhoneNumber() {
    return this.phoneNumber.toString();
  }

  public String getEmail() {
    return this.email.toString();
  }

  public String getName() {
    return this.name;
  }


  public double getDoubleValue() {
    return this.amount.getDoubleValue();
  }
}
