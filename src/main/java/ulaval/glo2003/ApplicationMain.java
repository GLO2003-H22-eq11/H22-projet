package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.health.api.HealthResource;
import ulaval.glo2003.product.api.product.ProductResource;
import ulaval.glo2003.seller.api.SellerResource;

import java.io.IOException;
import java.net.URI;

public class ApplicationMain {

  private static final String URL = "http://localhost:8080/";
  private static final String PACKAGE = "ulaval.glo2003";
  private static final AppContext appContext = new AppContext();

  public static void main(String[] args) throws IOException {
    ResourceConfig resourceConfig = setupResources();
    URI uri = URI.create(URL);

    HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
    server.start();
  }

  private static ResourceConfig setupResources() {
    HealthResource healthResource = new HealthResource();
    SellerResource sellerResource = createSellerResource();
    ProductResource productResource = createProductResource();

    return new ResourceConfig().packages(PACKAGE)
            .register(healthResource)
            .register(sellerResource)
            .register(productResource);
  }

  private static ProductResource createProductResource() {
    return new ProductResource(
            appContext.productFactory,
            appContext.productService,
            appContext.productAssembler,
            appContext.productIdFactory,
            appContext.productRequestValidator,
            appContext.productFiltersFactory
    );
  }

  private static SellerResource createSellerResource() {
    return new SellerResource(
            appContext.sellerFactory,
            appContext.sellerService,
            appContext.sellerAssembler,
            appContext.sellerIdFactory,
            appContext.sellerRequestValidator
    );
  }
}
