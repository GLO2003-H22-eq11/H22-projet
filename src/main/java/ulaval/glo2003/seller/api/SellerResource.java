package ulaval.glo2003.seller.api;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.SellerIdFactory;
import ulaval.glo2003.seller.service.SellerService;

import java.net.URI;


@Path("/sellers")
@Produces(MediaType.APPLICATION_JSON)
public class SellerResource {
  private static final String ENDPOINT = "sellers";
  private final SellerFactory sellerFactory;
  private final SellerService sellerService;
  private final SellerAssembler sellerAssembler;
  private final SellerIdFactory sellerIdFactory;
  private final SellerRequestValidator sellerRequestValidator;

  public SellerResource(
          SellerFactory sellerFactory,
          SellerService sellerService,
          SellerAssembler sellerAssembler,
          SellerIdFactory sellerIdFactory,
          SellerRequestValidator sellerRequestValidator) {
    this.sellerFactory = sellerFactory;
    this.sellerService = sellerService;
    this.sellerAssembler = sellerAssembler;
    this.sellerIdFactory = sellerIdFactory;
    this.sellerRequestValidator = sellerRequestValidator;
  }

  @POST
  public Response createSeller(SellerRequest sellerRequest) {
    try {
      this.sellerRequestValidator.validate(sellerRequest);

      Seller seller = this.sellerFactory.create(sellerRequest);
      this.sellerService.addSeller(seller);

      URI uri = URI.create(ENDPOINT + "/" + seller.getStringId());
      return Response.created(uri).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }

  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSellerById(@PathParam("id") String id) {
    try {
      SellerId sellerId = this.sellerIdFactory.create(id);

      Seller seller = this.sellerService.getSellerById(sellerId);

      SellerResponse sellerResponse = this.sellerAssembler.toResponse(seller);

      return Response.ok().entity(sellerResponse).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }
  }

  @DELETE
  @Path("/clear")
  public Response clear() {
    this.sellerService.deleteAll();
    return Response.ok().build();
  }
}
