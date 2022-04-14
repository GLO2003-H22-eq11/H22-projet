package ulaval.glo2003.product.api;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.QueryParam;
import ulaval.glo2003.exception.GenericException;
import ulaval.glo2003.product.api.assembler.ProductAssembler;
import ulaval.glo2003.offer.api.request.OfferRequest;
import ulaval.glo2003.product.api.request.ProductRequest;
import ulaval.glo2003.product.api.response.ProductWithSellerResponse;
import ulaval.glo2003.product.api.response.ProductWithViewsResponse;
import ulaval.glo2003.product.api.response.ProductsWithSellerResponse;
import ulaval.glo2003.offer.api.validator.OfferRequestValidator;
import ulaval.glo2003.product.api.validator.ProductRequestValidator;
import ulaval.glo2003.offer.domain.Offer;
import ulaval.glo2003.offer.domain.factory.OfferFactory;
import ulaval.glo2003.product.domain.Product;
import ulaval.glo2003.product.domain.factory.ProductFactory;
import ulaval.glo2003.product.domain.ProductFilters;
import ulaval.glo2003.product.domain.factory.ProductFiltersFactory;
import ulaval.glo2003.product.domain.ProductId;
import ulaval.glo2003.product.domain.factory.ProductIdFactory;
import ulaval.glo2003.product.domain.ProductWithSeller;
import ulaval.glo2003.product.service.ProductService;
import ulaval.glo2003.seller.domain.SellerId;
import ulaval.glo2003.seller.domain.factory.SellerIdFactory;

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
  private final SellerIdFactory sellerIdFactory;
  private final ProductFiltersFactory productFiltersFactory;
  private final OfferFactory offerFactory;
  private final OfferRequestValidator offerRequestValidator;

  public ProductResource(
          ProductFactory productFactory,
          ProductService productService,
          ProductAssembler productAssembler,
          ProductIdFactory productIdFactory,
          ProductRequestValidator productRequestValidator,
          SellerIdFactory sellerIdFactory, ProductFiltersFactory productFiltersFactory,
          OfferFactory offerFactory,
          OfferRequestValidator offerRequestValidator
  ) {
    this.productFactory = productFactory;
    this.productService = productService;
    this.productAssembler = productAssembler;
    this.productIdFactory = productIdFactory;
    this.productRequestValidator = productRequestValidator;
    this.sellerIdFactory = sellerIdFactory;
    this.productFiltersFactory = productFiltersFactory;
    this.offerFactory = offerFactory;
    this.offerRequestValidator = offerRequestValidator;
  }

  @POST
  public Response createProduct(ProductRequest productRequest, @HeaderParam("X-Seller-Id") String sellerIdString) {
    try {
      this.productRequestValidator.validate(productRequest);

      Product product = this.productFactory.create(
              sellerIdString,
              productRequest.title,
              productRequest.description,
              productRequest.suggestedPrice,
              productRequest.categories
      );

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

      ProductWithSeller productWithSeller = this.productService.getProductWithSeller(productId);

      ProductWithSellerResponse productWithSellerResponse = this.productAssembler
              .toProductWithSellerResponse(productWithSeller);

      return Response.ok().entity(productWithSellerResponse).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }
  }

  @GET
  @Path("/views")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSellerProductsViews(@HeaderParam("X-Seller-Id") String sellerIdString) {
    try {
      SellerId sellerId = this.sellerIdFactory.create(sellerIdString);

      List<Product> products = this.productService.getProductsBySellerId(sellerId);

      List<ProductWithViewsResponse> productWithViewResponses =
              this.productAssembler.toProductsWithViewsResponse(products);

      return Response.ok().entity(productWithViewResponses).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFilteredProducts(@QueryParam("sellerId") String sellerId,
                                      @QueryParam("title") String title,
                                      @QueryParam("categories") List<String> categories,
                                      @QueryParam("minPrice") Double minPrice,
                                      @QueryParam("maxPrice") Double maxPrice) {

    try {
      this.productRequestValidator.validatePrices(minPrice, maxPrice);

      ProductFilters productFilters = this.productFiltersFactory.create(
              sellerId, title, categories, minPrice, maxPrice
      );

      List<ProductWithSeller> products = this.productService.getFilteredProducts(productFilters);

      ProductsWithSellerResponse productsWithSellerResponse = this.productAssembler.toProductsResponse(products);

      return Response.ok().entity(productsWithSellerResponse).build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }
  }

  @POST
  @Path("/{productId}/offers")
  @Produces(MediaType.APPLICATION_JSON)
  public Response createOffer(OfferRequest offerRequest, @PathParam("productId") String productId) {
    try {
      this.offerRequestValidator.validate(offerRequest);

      Offer offer = this.offerFactory.create(
              offerRequest.name,
              offerRequest.email,
              offerRequest.phoneNumber,
              offerRequest.amount,
              offerRequest.message,
              productId
      );

      this.productService.createOffer(offer);

      return Response.ok().build();
    } catch (GenericException e) {
      return Response.status(e.getStatus()).entity(e.getErrorResponse()).build();
    }
  }

  @DELETE
  @Path("/clear")
  public Response clear() {
    this.productService.deleteAll();
    return Response.ok().build();
  }
}
