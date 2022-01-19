package ulaval.glo2003.seller.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerIdFactory;
import ulaval.glo2003.seller.service.SellerService;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Mock
    private SellerAssembler sellerAssembler;

    @Mock
    private SellerIdFactory sellerIdFactory;

    private SellerResource sellerResource;

    private final static String A_SELLER_STRING_ID = "5a3e3b0b-19a6-46cd-a0fe-bf16f42ba492";
    private final static SellerId A_SELLER_ID = new SellerId(A_SELLER_STRING_ID);


    @BeforeEach
    public void setUp() {
        this.sellerResource = new SellerResource(
                this.sellerFactory,
                this.sellerService,
                this.sellerAssembler,
                this.constraintsValidator,
                this.sellerIdFactory
        );
    }

    @Test
    public void givenASellerRequest_whenCreateSeller_thenShouldAddSeller() throws GenericException {
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


    @Test
    public void givenASellerId_whenGetSellerById_thenShouldGetSellerById() {
        givenASellerId();

        this.sellerResource.getSellerById(A_SELLER_STRING_ID);

        verify(this.sellerService).getSellerById(A_SELLER_ID);
    }

    @Test
    public void givenASellerId_whenGetSellerById_thenShouldReturnSellerResponse() {
        givenASellerId();
        SellerResponse expectedSellerResponse = getASellerResponse();
        given(this.sellerService.getSellerById(A_SELLER_ID)).willReturn(this.seller);
        given(this.sellerAssembler.toResponse(this.seller)).willReturn(expectedSellerResponse);

        SellerResponse actualSellerResponse = this.sellerResource.getSellerById(A_SELLER_STRING_ID);

        assertEquals(expectedSellerResponse, actualSellerResponse);
    }

    private SellerResponse getASellerResponse() {
        String aName = "Captain Barbosa";
        String aBio = "a biography";
        LocalDateTime aCreatedDate = LocalDateTime.now();
        return new SellerResponse(A_SELLER_STRING_ID, aName, aBio,aCreatedDate.toString());
    }

    private void givenASellerId() {
        given(this.sellerIdFactory.create(A_SELLER_STRING_ID)).willReturn(A_SELLER_ID);
    }
}