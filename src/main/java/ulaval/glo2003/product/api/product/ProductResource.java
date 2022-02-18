package ulaval.glo2003.product.api.product;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.ProductFilter;
import ulaval.glo2003.product.domain.product.ProductId;
import ulaval.glo2003.product.domain.product.ProductIdFactory;
import ulaval.glo2003.product.service.ProductService;
import ulaval.glo2003.seller.domain.Seller;

import java.net.URI;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
  private static final String ENDPOINT = "products";
  private final ProductFactory productFactory;
  private final ProductService productService;
  private final ProductAssembler productAssembler;
  private final ProductRequestValidator productRequestValidator;
  private final ProductIdFactory productIdFactory;
  private final ProductFilterFactory productFilterFactory;

  public ProductResource(
          ProductFactory productFactory,
          ProductService productService,
          ProductAssembler productAssembler,
          ProductIdFactory productIdFactory,
          ProductRequestValidator productRequestValidator,
          ProductFilterFactory productFilterFactory) {
    this.productFactory = productFactory;
    this.productService = productService;
    this.productAssembler = productAssembler;
    this.productIdFactory = productIdFactory;
    this.productRequestValidator = productRequestValidator;
    this.productFilterFactory = productFilterFactory;
  }

  @POST
  public Response createProduct(ProductRequest productRequest, @HeaderParam("X-Seller-Id") String sellerIdString) {
    try {
      this.productRequestValidator.validate(productRequest);

      Product product = this.productFactory.create(productRequest, sellerIdString);

      this.productService.addProduct(product);

      URI uri = URI.create(ENDPOINT + "/" + product.getStringId());
      return Response.created(uri).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getProductById(@PathParam("id") String id) {
    try {
      ProductId productId = this.productIdFactory.create(id);

      Product product = this.productService.getProductById(productId);
      Seller seller = this.productService.getProductOwner(product.getSellerId());

      ProductResponse productResponse = this.productAssembler.toResponse(product, seller);

      return Response.ok().entity(productResponse).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }
  }

  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFilterProducts(@QueryParam("sellerId") String sellerId,
                                    @QueryParam("title") String title,
                                    @QueryParam("categories") List<String> categories,
                                    @QueryParam("minPrice") int minPrice,
                                    @QueryParam("maxPrice") int maxPrice) {

      ProductFilter productFilter = this.productFilterFactory.create(sellerId, title, categories, minPrice, maxPrice);

      List<Product> products = this.productService.getFilterProducts(productFilter);

      return Response.ok().build();
  }
}
