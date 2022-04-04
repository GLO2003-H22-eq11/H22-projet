package ulaval.glo2003.offer.infrastructure.mongoDb;

import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.main.domain.Amount;
import ulaval.glo2003.offer.domain.Buyer;
import ulaval.glo2003.offer.domain.Email;
import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.OfferId;
import ulaval.glo2003.offer.domain.PhoneNumber;
import ulaval.glo2003.product.domain.ProductId;

import java.time.Instant;

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
            Instant.parse(offerEntity.getCreatedAt()),
            new Buyer(
                    offerEntity.getName(),
                    new Email(offerEntity.getEmail()),
                    new PhoneNumber(offerEntity.getPhoneNumber())
            )
    );
  }
}
