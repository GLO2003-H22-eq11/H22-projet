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


    private SellerResource sellerResource;
    private final static String aStringSellerId = "5a3e3b0b-19a6-46cd-a0fe-bf16f42ba492";
    private final static SellerId aSellerId = new SellerId(aStringSellerId);


    @BeforeEach
    public void setUp() {
        this.sellerResource = new SellerResource(this.sellerFactory, this.sellerService, this.constraintsValidator, this.sellerAssembler);
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

        this.sellerResource.getSellerById(aStringSellerId);

        verify(this.sellerService).getSellerById(aSellerId);
    }

    @Test
    public void givenASellerId_whenGetSellerById_thenShouldReturnSellerResponse() {
        String aName = "Captain Barbosa";
        String aBio = "a biography";
        LocalDateTime aCreatedDate = LocalDateTime.now();
        SellerResponse expectedSellerResponse = new SellerResponse(aStringSellerId, aName, aBio,aCreatedDate.toString());
        given(this.sellerService.getSellerById(aSellerId)).willReturn(this.seller);
        given(this.sellerAssembler.toResponse(this.seller)).willReturn(expectedSellerResponse);

        SellerResponse actualSellerResponse = this.sellerResource.getSellerById(aStringSellerId);

        assertEquals(expectedSellerResponse, actualSellerResponse);
    }
}