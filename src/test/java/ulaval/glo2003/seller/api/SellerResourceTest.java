package ulaval.glo2003.seller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
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
    private SellerFactory sellerFactory;

    @Mock
    private SellerService sellerService;

    @Mock
    private ConstraintsValidator constraintsValidator;

    private SellerResource sellerResource;

    @BeforeEach
    public void setUp() {
        this.sellerResource = new SellerResource(this.sellerFactory, this.sellerService, this.constraintsValidator, this.sellerAssembler);
    }

    @Test
    public void givenASellerRequest_whenAddSeller_thenShouldCreateSeller() throws GenericException {
        given(this.sellerFactory.create(sellerRequest)).willReturn(this.seller);

        this.sellerResource.createSeller(this.sellerRequest);

        verify(this.sellerService).addSeller(this.seller);
    }

    @Test
    public void givenASellerRequest_whenAddSeller_thenShouldCallTheConstraintValidator() throws GenericException {
        given(this.sellerFactory.create(sellerRequest)).willReturn(this.seller);

        this.sellerResource.createSeller(this.sellerRequest);

        verify(this.constraintsValidator).validate(this.sellerRequest);
    }

}