package ulaval.glo2003.seller.domain.factory;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.exceptions.SellerIsMinorException;
import ulaval.glo2003.util.DateParser;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;

public class SellerFactory {
  private static final int MAJOR_AGE = 18;
  private final SellerIdFactory sellerIdFactory;

  public SellerFactory(SellerIdFactory sellerIdFactory) {
    this.sellerIdFactory = sellerIdFactory;
  }

  public Seller create(String name, String bio, String birthDateString) throws GenericException {
    LocalDate birthDate = DateParser.format(birthDateString);
    int age = Period.between(birthDate, LocalDate.now()).getYears();
    if (age >= MAJOR_AGE) {
      return new Seller(
              this.sellerIdFactory.create(),
              name,
              bio,
              birthDate,
              Instant.now()
      );
    }
    throw new SellerIsMinorException();
  }
}
