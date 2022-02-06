package ulaval.glo2003.seller.api;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.util.DateParser;
import ulaval.glo2003.util.exceptions.InvalidDateFormatException;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class SellerFactory {

  public Seller create(SellerRequest sellerRequest) throws InvalidDateFormatException {
    return new Seller(
            new SellerId(),
            sellerRequest.name,
            sellerRequest.bio,
            DateParser.format(sellerRequest.birthDate),
            LocalDateTime.now(),
            new LinkedList<>()
    );
  }
}
