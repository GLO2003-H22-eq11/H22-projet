package ulaval.glo2003.seller.api;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.util.DateParser;

import java.time.LocalDateTime;

public class SellerFactory {

  public Seller create(SellerRequest sellerRequest) throws GenericException {
    return new Seller(
            new SellerId(),
            sellerRequest.name,
            sellerRequest.bio,
            DateParser.format(sellerRequest.birthDate),
            LocalDateTime.now()
    );
  }
}
