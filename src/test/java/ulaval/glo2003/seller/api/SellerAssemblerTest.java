package ulaval.glo2003.seller.api;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SellerAssemblerTest {
    private final SellerAssembler sellerAssembler = new SellerAssembler();

    @Test
    public void givenASeller_whenAssemblingToResponse_thenShouldAssembleWithCorrespondingParameters() {
        SellerId aSellerId = new SellerId();
        String aName = "Captain Barbosa";
        String aBio = "a biography";
        LocalDateTime aCreatedDate = LocalDateTime.now();
        LocalDate aBirthdate = LocalDate.of(2000, 6, 10);
        Seller aSeller = new Seller(aSellerId, aName, aBio, aBirthdate, aCreatedDate);
        SellerResponse expectedSellerResponse = new SellerResponse(aSellerId.toString(),
                aName,
                aCreatedDate.toString(),
                aBio);

        SellerResponse actualSellerResponse = this.sellerAssembler.toResponse(aSeller);

        assertEquals(expectedSellerResponse, actualSellerResponse);
    }
}