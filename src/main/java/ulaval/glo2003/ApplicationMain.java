package ulaval.glo2003;

import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.health.api.HealthResource;
import ulaval.glo2003.product.api.ProductResource;
import ulaval.glo2003.seller.api.SellerResource;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.net.URI;

public class ApplicationMain {

  private static final String PORT = System.getenv().getOrDefault("PORT", "8080");
  private static final String PACKAGE = "ulaval.glo2003";
  private static final AppContext appContext = new AppContext();
  private static final Logger logger = LogManager.getLogger(ApplicationMain.class);

  public static void main(String[] args) throws IOException {
    logger.trace("Starting application.");
    ResourceConfig resourceConfig = setupResources();
    URI uri;
    if (PORT.equals("8080")) {
      uri = UriBuilder.fromUri("http://localhost").port(Integer.parseInt(PORT)).build();
    } else {
      uri = UriBuilder.fromUri("http://0.0.0.0").port(Integer.parseInt(PORT)).build();
    }
    HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
    server.start();
  }

  private static ResourceConfig setupResources() {
    HealthResource healthResource = new HealthResource(appContext.databaseConnection);
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
            appContext.productFiltersFactory,
            appContext.offerFactory,
            appContext.offerRequestValidator
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
