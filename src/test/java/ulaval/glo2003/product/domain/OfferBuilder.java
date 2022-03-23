package ulaval.glo2003.product.domain;

public class OfferBuilder {
  private ProductId productId;
  private final String name;
  private final Email email;
  private final PhoneNumber phoneNumber;
  private Amount amount;
  private final String message;


  public OfferBuilder() {
    this.productId = new ProductId();
    this.name = "MARINO";
    this.email = new Email("CapatinBarbossa@email.ca");
    this.phoneNumber = new PhoneNumber("4181234567");
    this.amount = Amount.fromDouble(10.0);
    this.message = "This is a message";
  }

  public OfferBuilder withAmount(Amount amount) {
    this.amount = amount;
    return this;
  }

  public OfferBuilder withProductId(ProductId productId) {
    this.productId = productId;
    return this;
  }

  public Offer build() {
    return new Offer(
            this.productId,
            this.name,
            this.email,
            this.phoneNumber,
            this.amount,
            this.message
    );
  }
}
