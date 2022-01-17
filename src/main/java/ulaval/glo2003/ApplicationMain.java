package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.health.api.HealthResource;
import ulaval.glo2003.seller.api.SellerAssembler;
import ulaval.glo2003.seller.api.SellerResource;
import ulaval.glo2003.seller.domain.SellerRepository;
import ulaval.glo2003.seller.infrastructure.inMemory.InMemorySellerRepository;
import ulaval.glo2003.seller.service.SellerService;
import ulaval.glo2003.exception.ConstraintsValidator;

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
        SellerAssembler sellerAssembler = new SellerAssembler();
        SellerRepository sellerRepository = new InMemorySellerRepository();
        SellerService sellerService = new SellerService(sellerRepository);
        ConstraintsValidator constraintsValidator = new ConstraintsValidator();
        SellerResource sellerResource = new SellerResource(sellerAssembler, sellerService, constraintsValidator);

        return new ResourceConfig()
                .packages(PACKAGE).register(healthResource).register(sellerResource);
    }
}
