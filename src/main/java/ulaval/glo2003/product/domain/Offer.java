package ulaval.glo2003.product.domain;

import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.main.domain.Email;
import ulaval.glo2003.main.domain.PhoneNumber;

public class Offer {
  private final String name;
  private final Email email;
  private final PhoneNumber phoneNumber;
  private final Amount amount;
  private final String message;

  public Offer(String name, Email email, PhoneNumber phoneNumber, Amount amount, String message) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.amount = amount;
    this.message = message;
  }

  public Amount getAmount() {
    return this.amount;
  }
}