package pt.isel.ls.user.io;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.http.ExecutionServlet;
import pt.isel.ls.http.HttpServer;

import static java.lang.System.getenv;
/**
 * @author Tede Morgado
 *         Created at 09/06/2016
 */
public class AppServer
{
    private static final Logger _logger = LoggerFactory.getLogger(AppServer.class);
    public static void main(String[] args) throws Exception {


        String portDef = System.getenv("PORT");
        int port = portDef != null ? Integer.valueOf(portDef) : 8080;
        _logger.info("------------------------PORT = '{}'-----------------------", port);
        Server server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(ExecutionServlet.class, "/*");
        server.start();
    }
}
