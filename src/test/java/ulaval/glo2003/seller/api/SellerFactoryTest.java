package ulaval.glo2003.seller.api;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SellerFactoryTest {

  private final SellerFactory sellerFactory = new SellerFactory();

  @Test
  public void givenASellerRequest_whenCreate_thenShouldCreateWithCorrespondingParameters() {
    String aName = "Captain Barbosa";
    String aBio = "a biography";
    String aBirthdate = "10/06/2000";
    SellerRequest aSellerRequest = new SellerRequest();
    aSellerRequest.bio = aBio;
    aSellerRequest.name = aName;
    aSellerRequest.birthDate = aBirthdate;
    LocalDate birthDate = LocalDate.of(2000, 6, 10);
    SellerId aSellerId = new SellerId();
    LocalDateTime aCreationDate = LocalDateTime.now();
    Seller expectedSeller = new Seller(aSellerId, aName, aBio, birthDate, aCreationDate);

    Seller actualSeller = this.sellerFactory.create(aSellerRequest);

    assertEquals(expectedSeller, actualSeller);
  }
}