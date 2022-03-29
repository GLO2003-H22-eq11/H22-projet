package ulaval.glo2003.product.infrastructure.mongodb;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.Amount;
import ulaval.glo2003.product.domain.Buyer;
import ulaval.glo2003.product.domain.Email;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.OfferId;
import ulaval.glo2003.product.domain.PhoneNumber;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.infrastructure.mongodb.entity.OfferEntity;
import ulaval.glo2003.util.DateParser;

public class MongoDbOfferAssembler {

  public OfferEntity toEntity(Offer offer) {
    return new OfferEntity(
            offer.getOfferId().toString(),
            offer.getProductId().toString(),
            offer.getBuyerName(),
            offer.getBuyerEmail(),
            offer.getBuyerPhoneNumber(),
            offer.getAmount().getDoubleValue(),
            offer.getMessage(),
            offer.getCreatedAt()
    );
  }

  public Offer toDomain(OfferEntity offerEntity) throws GenericException {
    return new Offer(
            new OfferId(offerEntity.getOfferId()),
            new ProductId(offerEntity.getProductId()),
            Amount.fromDouble(offerEntity.getAmount()),
            offerEntity.getMessage(),
            DateParser.formatLocalDateTime(offerEntity.getCreatedAt()),
            new Buyer(
                    offerEntity.getName(),
                    new Email(offerEntity.getEmail()),
                    new PhoneNumber(offerEntity.getPhoneNumber())
            )
    );
  }
}
