package ulaval.glo2003.seller.api;

import ulaval.glo2003.seller.domain.Seller;

public class SellerAssembler {
    public SellerResponse toResponse(Seller seller) {
        return new SellerResponse(
                seller.getSellerId().toString(),
                seller.getName(),
                seller.getCreatedAt().toString(),
                seller.getBio()
                );
    }
}
