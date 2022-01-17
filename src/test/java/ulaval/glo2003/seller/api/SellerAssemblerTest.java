package ulaval.glo2003.seller.api;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.seller.domain.Seller;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SellerAssemblerTest {

  private final SellerAssembler sellerAssembler = new SellerAssembler();

  @Test
  public void givenASellerRequest_whenAssembleToInternal_thenShouldAssembleWithCorrespondingParameters() {
    String aName = "Captain Barbosa";
    String aBio = "a biography";
    String aBirthdate = "10/06/2000";
    SellerRequest aSellerRequest = new SellerRequest(aName, aBio, aBirthdate);
    LocalDate birthDate = LocalDate.of(2000, 6, 10);
    Seller expectedSeller = new Seller(aName, aBio, birthDate);

    Seller actualSeller = this.sellerAssembler.assembletoInternal(aSellerRequest);

    assertEquals(expectedSeller, actualSeller);
  }
}