package ulaval.glo2003.seller.domain;

import lombok.EqualsAndHashCode;
import ulaval.glo2003.exception.GenericException;

import java.time.LocalDate;
import java.time.Period;

@EqualsAndHashCode
public class Seller {
  private final String name;
  private final String bio;
  private final LocalDate birthDate;
  private final static int MAJOR_AGE = 18;

  public Seller(String name, String bio, LocalDate birthDate) {
    this.name = name;
    this.bio = bio;
    this.birthDate = birthDate;
  }

  private int getAge() {
    Period period = Period.between(this.birthDate, LocalDate.now());

    return period.getYears();
  }

  public void verifyIfSellerIsMajor() throws GenericException {
    if (!(this.getAge() >= MAJOR_AGE)) {
      throw new SellerIsMinorException();
    }
  }
}
