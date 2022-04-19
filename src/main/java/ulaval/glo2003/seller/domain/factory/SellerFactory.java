package ulaval.glo2003.seller.domain.factory;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.exceptions.SellerIsMinorException;
import ulaval.glo2003.util.AgeVerificator;
import ulaval.glo2003.util.DateParser;

import java.time.Instant;
import java.time.LocalDate;

public class SellerFactory {
  private static final int MAJOR_AGE = 18;
  private final SellerIdFactory sellerIdFactory;

  public SellerFactory(SellerIdFactory sellerIdFactory) {
    this.sellerIdFactory = sellerIdFactory;
  }

  public Seller create(String name, String bio, String birthDateString) throws GenericException {
    LocalDate birthDate = DateParser.format(birthDateString);

    if (AgeVerificator.isYoungerThanAge(birthDate, MAJOR_AGE)) {
      throw new SellerIsMinorException();
    }

    return new Seller(
            this.sellerIdFactory.create(),
            name,
            bio,
            birthDate,
            Instant.now()
    );
  }
}
