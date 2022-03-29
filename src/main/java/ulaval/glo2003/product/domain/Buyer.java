package ulaval.glo2003.product.domain;

import java.util.Objects;

public class Buyer {
  private final String name;
  private final Email email;
  private final PhoneNumber phoneNumber;

  public Buyer(String name, Email email, PhoneNumber phoneNumber) {
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email.toString();
  }

  public String getPhoneNumber() {
    return this.phoneNumber.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Buyer buyer = (Buyer) o;
    return name.equals(buyer.name) && email.equals(buyer.email) && phoneNumber.equals(buyer.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, phoneNumber);
  }
}
