package ulaval.glo2003.product.domain;

import java.util.Objects;

public class PhoneNumber {
  private final String phoneNumber;

  public PhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String toString() {
    return this.phoneNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PhoneNumber that = (PhoneNumber) o;
    return phoneNumber.equals(that.phoneNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phoneNumber);
  }
}
