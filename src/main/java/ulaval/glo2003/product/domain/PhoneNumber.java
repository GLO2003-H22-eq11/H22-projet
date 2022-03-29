package ulaval.glo2003.product.domain;

public class PhoneNumber {
  private final String phoneNumber;

  public PhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String toString() {
    return this.phoneNumber;
  }
}
