package ulaval.glo2003.product.api.assembler;

import org.jetbrains.annotations.NotNull;
import ulaval.glo2003.product.api.response.BuyerResponse;
import ulaval.glo2003.product.api.response.OfferResponse;
import ulaval.glo2003.product.api.response.OffersInformationResponse;
import ulaval.glo2003.product.api.response.OffersResponse;
import ulaval.glo2003.product.domain.Offer;
import ulaval.glo2003.product.domain.OffersSummary;
import ulaval.glo2003.product.domain.ProductWithOffers;

import java.util.ArrayList;
import java.util.List;

public class OffersAssembler {

  public OffersResponse toResponse(OffersSummary offersSummary) {
    return new OffersResponse(
            offersSummary.getMeanAmount(),
            offersSummary.getCount()
    );
  }

  public OffersInformationResponse toOffersInformationResponse(ProductWithOffers productWithOffers) {
    List<OfferResponse> offerResponses = getOfferResponses(productWithOffers);

    OffersInformationResponse offersInformationResponse = new OffersInformationResponse(
            productWithOffers.getMinOffer(), productWithOffers.getMaxOffer(), productWithOffers.getMean(),
            productWithOffers.getCount(), offerResponses
    );
    return offersInformationResponse;
  }

  @NotNull
  private List<OfferResponse> getOfferResponses(ProductWithOffers productWithOffers) {
    List<OfferResponse> offerResponses = new ArrayList<>();

    for (Offer offer : productWithOffers.getProductOffers()) {
      BuyerResponse buyerResponse = new BuyerResponse(offer.getName(), offer.getEmail(), offer.getPhoneNumber());

      OfferResponse offerResponse = new OfferResponse(offer.getId(), offer.getDoubleValue(), offer.getMessage(),
              buyerResponse);
      offerResponses.add(offerResponse);
    }
    return offerResponses;
  }
}
