package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.exception.ConstraintsValidator;
import ulaval.glo2003.health.api.HealthResource;
import ulaval.glo2003.seller.api.SellerAssembler;
import ulaval.glo2003.seller.api.SellerFactory;
import ulaval.glo2003.seller.api.SellerResource;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.infrastructure.inMemory.InMemorySellerRepository;
import ulaval.glo2003.seller.service.SellerService;

import java.io.IOException;
import java.net.URI;

public class ApplicationMain {

    private static final String URL = "http://localhost:8080/";
    private static final String PACKAGE = "ulaval.glo2003";

    public static void main(String[] args) throws IOException {
        ResourceConfig resourceConfig = setupResources();
        URI uri = URI.create(URL);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
    }

    private static ResourceConfig setupResources() {
        HealthResource healthResource = new HealthResource();
        SellerResource sellerResource = createSellerResource();

        return new ResourceConfig()
                .packages(PACKAGE).register(healthResource).register(sellerResource);
    }

    private static SellerResource createSellerResource() {
        SellerFactory sellerFactory = new SellerFactory();
        SellerRepository sellerRepository = new InMemorySellerRepository();
        SellerService sellerService = new SellerService(sellerRepository);
        ConstraintsValidator constraintsValidator = new ConstraintsValidator();
        SellerAssembler sellerAssembler = new SellerAssembler();

        return new SellerResource(sellerFactory, sellerService, constraintsValidator);
    }
}
