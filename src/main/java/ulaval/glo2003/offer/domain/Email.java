package ulaval.glo2003.offer.domain;

import java.util.Objects;

public class Email {
  private final String email;

  public Email(String email) {
    this.email = email;
  }

  public String toString() {
    return this.email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Email email1 = (Email) o;
    return email.equals(email1.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }
}
