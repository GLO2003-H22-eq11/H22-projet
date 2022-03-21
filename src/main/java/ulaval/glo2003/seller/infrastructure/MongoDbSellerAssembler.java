package ulaval.glo2003.seller.infrastructure;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerIdFactory;
import ulaval.glo2003.seller.domain.exceptions.InvalidSellerIdException;
import ulaval.glo2003.seller.infrastructure.mongoDb.entity.SellerEntity;

public class MongoDbSellerAssembler {

  private final SellerIdFactory sellerIdFactory;

  public MongoDbSellerAssembler(SellerIdFactory sellerIdFactory) {
    this.sellerIdFactory = sellerIdFactory;
  }


  public SellerEntity toEntity(Seller seller) {
    return new SellerEntity(seller.getStringSellerId(), seller.getName(), seller.getBio(),
            seller.getBirthDate(), seller.getCreatedAt());
  }

  public Seller toSeller(SellerEntity sellerEntity) throws InvalidSellerIdException {
    SellerId sellerId = this.sellerIdFactory.create(sellerEntity.getSellerId());
    return new Seller(sellerId, sellerEntity.getName(), sellerEntity.getBio(), sellerEntity.getBirthDate(),
            sellerEntity.getCreatedAt());
  }

}
