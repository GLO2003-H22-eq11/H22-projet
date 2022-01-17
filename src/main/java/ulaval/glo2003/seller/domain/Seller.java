package ulaval.glo2003.seller.domain;

import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@EqualsAndHashCode
public class Seller {
  private final SellerIdentifier sellerIdentifier;
  private final String name;
  private final String bio;
  private final LocalDate birthDate;
  private final LocalDateTime createdAt;
  private final static int MAJOR_AGE = 18;

  public Seller(SellerIdentifier sellerIdentifier, String name, String bio, LocalDate birthDate, LocalDateTime createdAt) {
    this.sellerIdentifier = sellerIdentifier;
    this.name = name;
    this.bio = bio;
    this.birthDate = birthDate;
    this.createdAt = createdAt;
  }

  public SellerIdentifier getSellerIdentifier() {
    return this.sellerIdentifier;
  }

  private int getAge() {
    Period period = Period.between(this.birthDate, LocalDate.now());

    return period.getYears();
  }

  public boolean isMajor() {
    return this.getAge() >= MAJOR_AGE;
  }
}
