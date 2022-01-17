package ulaval.glo2003;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.health.api.HealthResource;

import java.io.IOException;
import java.net.URI;

public class ApplicationMain {

    private static final String URL = "http://localhost:8080/";
    private static final String PACKAGE = "ulaval.glo2003";

    public static void main(String[] args) throws IOException {
        HealthResource healthResource = new HealthResource();
        ResourceConfig resourceConfig = new ResourceConfig()
                .packages(PACKAGE).register(healthResource);
        URI uri = URI.create(URL);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
        server.start();
    }
}
