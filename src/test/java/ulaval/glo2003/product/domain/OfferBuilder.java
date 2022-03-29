package ulaval.glo2003.product.domain;

import java.time.Instant;

public class OfferBuilder {
  private final OfferId offerId;
  private ProductId productId;
  private Amount amount;
  private final String message;
  private final Buyer buyer;


  public OfferBuilder() {
    this.productId = new ProductId();
    this.offerId = new OfferId();
    this.amount = Amount.fromDouble(10.0);
    this.message = "This is a message";
    this.buyer = new Buyer("MARINO", new Email("CapatinBarbossa@email.ca"), new PhoneNumber("14181234567"));
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
            offerId,
            this.productId,
            this.amount,
            this.message,
            Instant.now(),
            this.buyer
    );
  }
}
