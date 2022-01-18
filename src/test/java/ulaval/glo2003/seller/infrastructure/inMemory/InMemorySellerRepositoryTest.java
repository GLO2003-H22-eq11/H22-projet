package ulaval.glo2003.seller.infrastructure.inMemory;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InMemorySellerRepositoryTest {
    private final InMemorySellerRepository inMemorySellerRepository = new InMemorySellerRepository();

    @Test
    public void givenASellerAndAnId_whenFindById_thenShouldFindTheSeller(){
        SellerId aSellerId = new SellerId();
        String aName = "Captain Barbosa";
        String aBio = "a biography";
        LocalDateTime aCreatedDate = LocalDateTime.now();
        LocalDate aBirthdate = LocalDate.of(2000, 6, 10);
        Seller aSeller = new Seller(aSellerId, aName, aBio, aBirthdate, aCreatedDate);
        this.inMemorySellerRepository.save(aSeller);

        Seller actualSeller = this.inMemorySellerRepository.findById(aSellerId);

        assertEquals(aSeller, actualSeller);
    }

}