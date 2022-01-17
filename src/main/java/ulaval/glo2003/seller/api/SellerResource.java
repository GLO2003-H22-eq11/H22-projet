package ulaval.glo2003.seller.api;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.service.SellerService;


@Path("/sellers")
@Produces(MediaType.APPLICATION_JSON)
public class SellerResource {
  private final SellerAssembler sellerAssembler;
  private final SellerService sellerService;

  public SellerResource(SellerAssembler sellerAssembler, SellerService sellerService) {
    this.sellerAssembler = sellerAssembler;
    this.sellerService = sellerService;
  }

  @POST
  public Response createSeller(SellerRequest sellerRequest) {
    Seller seller = sellerAssembler.assembletoInternal(sellerRequest);

    this.sellerService.addSeller(seller);

    return Response.ok().build();
  }
}
