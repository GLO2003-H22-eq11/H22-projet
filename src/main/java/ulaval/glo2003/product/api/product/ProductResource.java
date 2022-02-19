package ulaval.glo2003.product.api.product;

import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.QueryParam;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.domain.product.Product;
import ulaval.glo2003.product.domain.product.productId.ProductId;
import ulaval.glo2003.product.domain.product.productWithSeller.ProductWithSeller;
import ulaval.glo2003.product.domain.product.productId.ProductIdFactory;
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

  public ProductResource(
          ProductFactory productFactory,
          ProductService productService,
          ProductAssembler productAssembler,
          ProductIdFactory productIdFactory,
          ProductRequestValidator productRequestValidator) {
    this.productFactory = productFactory;
    this.productService = productService;
    this.productAssembler = productAssembler;
    this.productIdFactory = productIdFactory;
    this.productRequestValidator = productRequestValidator;
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
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFilterProducts(@DefaultValue("") @QueryParam("sellerId") String sellerId,
                                    @DefaultValue("") @QueryParam("title") String title,
                                    @DefaultValue("null") @QueryParam("categories") List<String> categories,
                                    @DefaultValue("0") @QueryParam("minPrice") int minPrice,
                                    @DefaultValue("0") @QueryParam("maxPrice") int maxPrice) {

    try {
      List<ProductWithSeller> products = this.productService.getFilterProducts(
              sellerId,
              title,
              categories,
              minPrice,
              maxPrice);

      ProductsResponse productsResponse = this.productAssembler.toProductsResponse(products);

      return Response.ok().entity(productsResponse).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }

  }
}
