package pt.isel.ls.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Created by Dani on 17-05-2016.
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

    public void closeServer() throws Exception {
        server.stop();
    }
}
