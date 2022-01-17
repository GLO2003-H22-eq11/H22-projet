package ulaval.glo2003.seller.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.service.SellerService;

import java.net.URI;


@Path("/sellers")
@Produces(MediaType.APPLICATION_JSON)
public class SellerResource {
  private static final String endpoint = "sellers";
  private final SellerFactory sellerFactory;
  private final SellerService sellerService;
  private final SellerAssembler sellerAssembler;
  private final ConstraintsValidator constraintsValidator;

  public SellerResource(SellerFactory sellerFactory, SellerService sellerService, SellerAssembler sellerAssembler, ConstraintsValidator constraintsValidator) {
    this.sellerFactory = sellerFactory;
    this.sellerService = sellerService;
    this.sellerAssembler = sellerAssembler;
    this.constraintsValidator = constraintsValidator;
  }

  @POST
  public Response createSeller(SellerRequest sellerRequest) {
    try {
      this.constraintsValidator.validate(sellerRequest);

      Seller seller = this.sellerFactory.create(sellerRequest);
      this.sellerService.addSeller(seller);

      URI uri = URI.create(endpoint + "/" + seller.getStringId());
      return Response.created(uri).build();
    } catch (GenericException e) {
      return Response.ok(e.getErrorResponse()).build();
    }

  }

  @GET
  public SellerResponse getSellerById(String id) {
    SellerId  sellerId = new SellerId(id);

    Seller seller = this.sellerService.getSellerById(sellerId);

    return this.sellerAssembler.toResponse(seller);
  }
}
