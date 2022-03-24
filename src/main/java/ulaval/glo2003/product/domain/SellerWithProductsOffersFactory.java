package ulaval.glo2003.product.domain;

import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerWithProductsOffers;

import java.util.List;

public class SellerWithProductsOffersFactory {

  public SellerWithProductsOffers create(Seller seller, List<ProductWithOffers> productInformations) {
    return new SellerWithProductsOffers(
            seller.getSellerId(),
            seller.getName(),
            seller.getBio(),
            seller.getBirthDate(),
            seller.getCreatedAt(),
            productInformations
    );
  }
}
