package ulaval.glo2003.product.api.assembler;

import ulaval.glo2003.product.api.response.OfferResponse;
import ulaval.glo2003.product.api.response.OffersInformationResponse;
import ulaval.glo2003.product.api.response.OffersSummaryResponse;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.OffersInformation;
import ulaval.glo2003.product.domain.OffersSummary;

import java.util.stream.Collectors;


public class OffersAssembler {
  private final BuyerAssembler buyerAssembler;

  public OffersAssembler(BuyerAssembler buyerAssembler) {
    this.buyerAssembler = buyerAssembler;
  }

  public OffersSummaryResponse toResponse(OffersSummary offersSummary) {
    return new OffersSummaryResponse(
            offersSummary.getMeanAmount(),
            offersSummary.getCount()
    );
  }

  public OfferResponse toResponse(Offer offer) {
    return new OfferResponse(
            offer.getStringId(),
            offer.getAmountDoubleValue(),
            offer.getMessage(),
            this.buyerAssembler.toResponse(offer.getBuyer()),
            offer.getCreatedAt()
    );
  }

  public OffersInformationResponse toOffersInformationResponse(OffersInformation offersInformation) {
    return new OffersInformationResponse(
            offersInformation.getMin(),
            offersInformation.getMaxDoubleValue(),
            offersInformation.getMean(),
            offersInformation.getCount(),
            offersInformation.getOffers().stream().map(this::toResponse).collect(Collectors.toList())
    );
  }
}
