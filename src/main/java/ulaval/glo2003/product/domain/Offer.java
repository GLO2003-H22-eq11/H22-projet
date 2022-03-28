package ulaval.glo2003.product.domain;

public class Offer {
  private final OfferId offerId;
  private final ProductId productId;
  private final String name;
  private final Email email;
  private final PhoneNumber phoneNumber;
  private final Amount amount;
  private final String message;

  public Offer(
          OfferId offerId,
          ProductId productId,
          String name,
          Email email,
          PhoneNumber phoneNumber,
          Amount amount,
          String message
  ) {
    this.offerId = offerId;
    this.productId = productId;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.amount = amount;
    this.message = message;
  }

  public Amount getAmount() {
    return this.amount;
  }

  public ProductId getProductId() {
    return this.productId;
  }

  public OfferId getOfferId() {
    return this.offerId;
  }

  public String getName() {
    return this.name;
  }

  public Email getEmail() {
    return this.email;
  }

  public PhoneNumber getPhoneNumber() {
    return this.phoneNumber;
  }

  public String getMessage() {
    return this.message;
  }
}
