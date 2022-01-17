package ulaval.glo2003.seller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.service.SellerService;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SellerResourceTest {

    @Mock
    private Seller seller;

    @Mock
    private SellerRequest sellerRequest;

    @Mock
    private SellerAssembler sellerAssembler;

    @Mock
    private SellerService sellerService;

    private SellerResource sellerResource;

    @BeforeEach
    public void setUp() {
        this.sellerResource = new SellerResource(this.sellerAssembler, this.sellerService);
    }

    @Test
    public void givenASellerRequest_whenAddSeller_thenShouldCreateSeller() {
        given(this.sellerAssembler.assembleToInternal(sellerRequest)).willReturn(this.seller);

        this.sellerResource.createSeller(this.sellerRequest);

        verify(this.sellerService).addSeller(this.seller);
    }
}