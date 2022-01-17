package ulaval.glo2003.seller.api;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerIdentifier;
import ulaval.glo2003.util.DateParser;

import java.time.LocalDateTime;


public class SellerFactory {

  public Seller create(SellerRequest sellerRequest) {
    return new Seller(
            new SellerIdentifier(),
            sellerRequest.name,
            sellerRequest.bio,
            DateParser.format(sellerRequest.birthDate),
            LocalDateTime.now()
    );
  }
}
