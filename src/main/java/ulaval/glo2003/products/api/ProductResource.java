package ulaval.glo2003.products.api;

import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.products.domain.Product;
import ulaval.glo2003.products.service.ProductService;

import java.net.URI;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
  private static final String ENDPOINT = "products";
  private final ConstraintsValidator constraintsValidator;
  private final ProductFactory productFactory;
  private final ProductService productService;

  public ProductResource(
          ConstraintsValidator constraintsValidator,
          ProductFactory productFactory,
          ProductService productService
  ) {
    this.constraintsValidator = constraintsValidator;
    this.productFactory = productFactory;
    this.productService = productService;
  }

  @POST
  public Response createProduct(ProductRequest productRequest, @HeaderParam("X-Seller-Id") String sellerIdString) {
    try {
      this.constraintsValidator.validate(productRequest);

      Product product = this.productFactory.create(productRequest, sellerIdString);

      this.productService.addProduct(product);

      URI uri = URI.create(ENDPOINT + "/" + product.getStringId());
      return Response.created(uri).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }
  }
}
