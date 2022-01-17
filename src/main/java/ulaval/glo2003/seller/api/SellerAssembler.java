package ulaval.glo2003.seller.api;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.util.DateParser;


public class SellerAssembler {

  public Seller assembletoInternal(SellerRequest sellerRequest) {
    return new Seller(
            sellerRequest.name,
            sellerRequest.bio,
            DateParser.format(sellerRequest.birthDate)
    );
  }
}
