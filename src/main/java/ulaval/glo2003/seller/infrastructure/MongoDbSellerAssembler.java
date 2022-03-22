package ulaval.glo2003.seller.infrastructure;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.exceptions.InvalidSellerIdException;
import ulaval.glo2003.seller.infrastructure.mongoDb.entity.SellerEntity;

public class MongoDbSellerAssembler {

  public SellerEntity toEntity(Seller seller) {
    return new SellerEntity(seller.getStringSellerId(), seller.getName(), seller.getBio(),
            seller.getBirthDate(), seller.getCreatedAt());
  }

  public Seller toSeller(SellerEntity sellerEntity) throws InvalidSellerIdException {
    return new Seller(new SellerId(sellerEntity.getSellerId()), sellerEntity.getName(),
            sellerEntity.getBio(), sellerEntity.getBirthDate(),
            sellerEntity.getCreatedAt());
  }

}
