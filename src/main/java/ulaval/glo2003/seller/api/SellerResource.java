package ulaval.glo2003.seller.api;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.seller.domain.Seller;
import ulaval.glo2003.seller.service.SellerService;

import java.net.URI;


@Path("/sellers")
@Produces(MediaType.APPLICATION_JSON)
public class SellerResource {
  private static final String sellerEndpoint = "sellers";
  private final SellerFactory sellerFactory;
  private final SellerService sellerService;

  public SellerResource(SellerFactory sellerFactory, SellerService sellerService) {
    this.sellerFactory = sellerFactory;
    this.sellerService = sellerService;
  }

  @POST
  public Response createSeller(SellerRequest sellerRequest) {
    Seller seller = this.sellerFactory.create(sellerRequest);

    this.sellerService.addSeller(seller);

    URI uri = URI.create(sellerEndpoint + "/" + seller.getSellerId().toString());
    return Response.created(uri).build();
  }
}
