package ulaval.glo2003.seller.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerRepository;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    @Mock
    private Seller seller;

    @Mock
    private SellerRepository sellerRepository;

    private SellerService sellerService;

    @BeforeEach
    public void setUp() {
        this.sellerService = new SellerService(this.sellerRepository);
    }

    @Test
    public void givenAMajorSeller_whenAddSeller_thenShouldAddTheSeller() {
        BDDMockito.given(this.seller.isMajor()).willReturn(true);

        this.sellerService.addSeller(this.seller);

        verify(this.sellerRepository).save(this.seller);
    }

    @Test
    public void givenAMinorSeller_whenAddSeller_thenShouldAddTheSeller() {
        BDDMockito.given(this.seller.isMajor()).willReturn(false);

        this.sellerService.addSeller(this.seller);

        verify(this.sellerRepository, never()).save(this.seller);
    }

}