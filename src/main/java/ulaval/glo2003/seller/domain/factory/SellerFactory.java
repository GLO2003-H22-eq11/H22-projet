package ulaval.glo2003.seller.domain.factory;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.util.DateParser;

import java.time.Instant;

public class SellerFactory {
  private final SellerIdFactory sellerIdFactory;

  public SellerFactory(SellerIdFactory sellerIdFactory) {
    this.sellerIdFactory = sellerIdFactory;
  }

  public Seller create(String name, String bio, String birthDate) throws GenericException {
    return new Seller(
            this.sellerIdFactory.create(),
            name,
            bio,
            DateParser.format(birthDate),
            Instant.now()
    );
  }
}
