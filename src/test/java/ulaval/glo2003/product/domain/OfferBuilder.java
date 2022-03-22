package ulaval.glo2003.product.domain;

import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.main.domain.Email;
import ulaval.glo2003.main.domain.PhoneNumber;

public class OfferBuilder {
  private final String name;
  private final Email email;
  private final PhoneNumber phoneNumber;
  private Amount amount;
  private final String message;


  public OfferBuilder() {
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

  public Offer build() {
    return new Offer(
            this.name,
            this.email,
            this.phoneNumber,
            this.amount,
            this.message
    );
  }
}
