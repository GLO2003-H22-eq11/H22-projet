package ulaval.glo2003.seller.api;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.service.SellerService;
import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;


@Path("/sellers")
@Produces(MediaType.APPLICATION_JSON)
public class SellerResource {
  private final SellerAssembler sellerAssembler;
  private final SellerService sellerService;
  private final ConstraintsValidator constraintsValidator;

  public SellerResource(SellerAssembler sellerAssembler, SellerService sellerService, ConstraintsValidator constraintsValidator) {
    this.sellerAssembler = sellerAssembler;
    this.sellerService = sellerService;
    this.constraintsValidator = constraintsValidator;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createSeller(SellerRequest sellerRequest) {
    try {
      this.constraintsValidator.validate(sellerRequest);

      Seller seller = sellerAssembler.assembletoInternal(sellerRequest);
      this.sellerService.addSeller(seller);
      return Response.ok().build();
    } catch (GenericException e) {
      return Response.ok(e.getErrorResponse()).build();
    }
  }
}
