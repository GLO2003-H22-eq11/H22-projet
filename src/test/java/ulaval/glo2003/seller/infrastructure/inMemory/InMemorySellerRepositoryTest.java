package ulaval.glo2003.seller.infrastructure.inMemory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerBuilder;
import ulaval.glo2003.seller.domain.SellerId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemorySellerRepositoryTest {
    private final InMemorySellerRepository inMemorySellerRepository = new InMemorySellerRepository();

    private static final SellerId A_SELLER_ID = new SellerId();
    private static final Seller A_SELLER = new SellerBuilder().withSellerId(A_SELLER_ID).build();

    @BeforeEach
    public void setUp() {
        this.inMemorySellerRepository.save(A_SELLER);
    }

    @Test
    public void givenASellerAndAnId_whenFindById_thenShouldFindTheSeller(){
        Optional<Seller> actualSeller = this.inMemorySellerRepository.findById(A_SELLER_ID);

        assertEquals(A_SELLER, actualSeller.get());
    }
}
