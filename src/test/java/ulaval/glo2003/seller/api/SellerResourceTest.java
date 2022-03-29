package ulaval.glo2003.seller.api;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerBuilder;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerIdFactory;
import ulaval.glo2003.seller.domain.SellerWithProducts;
import ulaval.glo2003.seller.domain.exceptions.InvalidSellerIdException;
import ulaval.glo2003.seller.service.SellerService;


import java.util.Collections;

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
  private SellerAssembler sellerAssembler;

  @Mock
  private SellerIdFactory sellerIdFactory;

  @Mock
  private SellerRequestValidator sellerRequestValidator;

  @Mock
  private SellerWithProducts sellerWithProducts;

  @Mock
  private SellerWithProductsResponse sellerWithProductsResponse;

  private SellerResource sellerResource;

  private final static String A_SELLER_STRING_ID = "5a3e3b0b-19a6-46cd-a0fe-bf16f42ba492";

  @BeforeEach
  public void setUp() {
    this.sellerResource = new SellerResource(
            this.sellerFactory,
            this.sellerService,
            this.sellerAssembler,
            this.sellerIdFactory,
            this.sellerRequestValidator
    );
  }

  @Test
  public void givenASellerRequest_whenCreateSeller_thenShouldAddSeller() throws GenericException {
    given(this.sellerFactory.create(this.sellerRequest)).willReturn(this.seller);

    this.sellerResource.createSeller(this.sellerRequest);

    verify(this.sellerService).addSeller(this.seller);
  }

  @Test
  public void givenASellerRequest_whenAddSeller_thenShouldCallTheSellerRequestValidator() throws GenericException {
    given(this.sellerFactory.create(this.sellerRequest)).willReturn(this.seller);

    this.sellerResource.createSeller(this.sellerRequest);

    verify(this.sellerRequestValidator).validate(this.sellerRequest);
  }

  @Test
  public void givenASellerId_whenGetSellerById_thenShouldCreateSellerId() throws GenericException{
    this.sellerResource.getSellerById(A_SELLER_STRING_ID);

    verify(this.sellerIdFactory).create(A_SELLER_STRING_ID);
  }

  @Test
  public void givenASellerId_whenGetSellerById_thenShouldGetSellerById() throws GenericException {
    SellerId aSellerId = new SellerId(A_SELLER_STRING_ID);
    givenASellerId(aSellerId);

    this.sellerResource.getSellerById(A_SELLER_STRING_ID);

    verify(this.sellerService).getSellerById(aSellerId);
  }

  @Test
  public void givenASellerId_whenGetSellerById_thenShouldReturnSellerResponseWithEntity() throws GenericException {
    SellerId aSellerId = new SellerId(A_SELLER_STRING_ID);
    givenASellerId(aSellerId);
    Seller aSeller = givenASeller(aSellerId);
    SellerResponse aSellerResponse = givenASellerResponse(aSeller);

    Response expectedResponse = Response.ok().entity(aSellerResponse).build();
    Response actualResponse = this.sellerResource.getSellerById(A_SELLER_STRING_ID);

    assertEquals(expectedResponse.getEntity(), actualResponse.getEntity());
  }

  @Test
  public void givenASellerId_whenGetCurrentSeller_thenShouldCallTheSellerIdFactory() throws InvalidSellerIdException {
    this.sellerResource.getCurrentSeller(A_SELLER_STRING_ID);

    verify(this.sellerIdFactory).create(A_SELLER_STRING_ID);
  }

  @Test
  public void givenASellerId_whenGetCurrentSeller_thenShouldCallTheServiceToGetSellerProductsWithOffers() throws
          GenericException {
    SellerId aSellerId = new SellerId(A_SELLER_STRING_ID);
    givenASellerId(aSellerId);

    this.sellerResource.getCurrentSeller(A_SELLER_STRING_ID);

    verify(this.sellerService).getSellerWithProductsById(aSellerId);
  }

  @Test
  public void givenASellerId_whenGetCurrentSeller_thenShouldReturnAResponseWithTheRightEntity() throws
          GenericException {
    SellerId aSellerId = new SellerId(A_SELLER_STRING_ID);
    givenASellerId(aSellerId);
    given(this.sellerService.getSellerWithProductsById(aSellerId)).willReturn(this.sellerWithProducts);
    given(this.sellerAssembler.toSellerWithProductsResponse(this.sellerWithProducts)).willReturn(this.sellerWithProductsResponse);

    Response expectedResponse = Response.ok().entity(this.sellerWithProductsResponse).build();
    Response actualResponse = this.sellerResource.getCurrentSeller(A_SELLER_STRING_ID);

    assertEquals(expectedResponse.getEntity(), actualResponse.getEntity());
  }


  private SellerResponse givenASellerResponse(Seller aSeller) {
    SellerResponse aSellerResponse = new SellerResponse(A_SELLER_STRING_ID, "allo", "created", "bio", Collections.emptyList());
    given(this.sellerAssembler.toResponse(aSeller)).willReturn(aSellerResponse);
    return aSellerResponse;
  }

  private Seller givenASeller(SellerId sellerId) throws GenericException {
    Seller aSeller = new SellerBuilder().build();
    given(this.sellerService.getSellerById(sellerId)).willReturn(aSeller);
    return aSeller;
  }

  private void givenASellerId(SellerId aSellerId) throws GenericException {
    given(this.sellerIdFactory.create(A_SELLER_STRING_ID)).willReturn(aSellerId);
  }
}