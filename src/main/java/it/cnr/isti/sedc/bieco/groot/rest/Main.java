package it.cnr.isti.sedc.bieco.groot.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.grizzly.http.server.StaticHttpHandler;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    // public static final String BASE_URI = "http://127.0.0.1:8283/";
    public static final String BASE_URI = "http://0.0.0.0:8283/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer(String serverUri) {
        // create a resource config that scans for JAX-RS resources and providers
        // in it.cnr.isti.labsedc.concern.rest package
        final ResourceConfig rc = new ResourceConfig().packages("it.cnr.isti.sedc.bieco.groot.rest");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(serverUri), rc);
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        HttpServer server = null;
        try {
            server = startServer(BASE_URI);
            
            final StaticHttpHandler staticHandler = new StaticHttpHandler("main/webapp/admin");
            
            
            //server.getServerConfiguration().addHttpHandler(new StaticHttpHandler("src/main/webapp/admin"), "/admin");
            
            server.getServerConfiguration().addHttpHandler(staticHandler, "/admin");
            
            server.start();

            System.out.println(String.format("Jersey app started with endpoints available at " + "%s%nHit Ctrl-C to stop it...", BASE_URI));
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                server.shutdownNow();
            }
        }
    }
}