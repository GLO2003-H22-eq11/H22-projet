package ulaval.glo2003.seller.domain;

import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@EqualsAndHashCode
public class Seller {
  private final SellerId sellerId;
  private final String name;
  private final String bio;
  private final LocalDate birthDate;
  private final LocalDateTime createdAt;
  private final static int MAJOR_AGE = 18;

  public Seller(SellerId sellerId, String name, String bio, LocalDate birthDate, LocalDateTime createdAt) {
    this.sellerId = sellerId;
    this.name = name;
    this.bio = bio;
    this.birthDate = birthDate;
    this.createdAt = createdAt;
  }

  public SellerId getSellerId() {
    return this.sellerId;
  }

  private int getAge() {
    Period period = Period.between(this.birthDate, LocalDate.now());

    return period.getYears();
  }

  public boolean isMajor() {
    return this.getAge() >= MAJOR_AGE;
  }
}
