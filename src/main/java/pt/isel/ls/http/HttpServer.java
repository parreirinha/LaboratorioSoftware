package pt.isel.ls.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Class used to initiate an HTTP server and configures its port and handler.
 */
public class HttpServer {

    private Server server;

    public void initServer(int port) throws Exception {
        server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(ExecutionServlet.class, "/*");
        server.start();
    }

}
