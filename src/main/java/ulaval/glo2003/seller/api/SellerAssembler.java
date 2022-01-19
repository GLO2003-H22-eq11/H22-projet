package ulaval.glo2003.seller.api;

import ulaval.glo2003.seller.domain.Seller;

public class SellerAssembler {
    public SellerResponse toResponse(Seller seller) {
        return new SellerResponse(
                seller.getStringSellerId(),
                seller.getName(),
                seller.getStringCreatedAt(),
                seller.getBio()
                );
    }
}
