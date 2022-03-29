package ulaval.glo2003.product.domain;

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
}
