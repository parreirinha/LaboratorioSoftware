package pt.isel.ls.user.io;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.http.ExecutionServlet;

/**
 * @author Tede Morgado
 *         Created at 09/06/2016
 */
public class AppServer {
    private static final Logger _logger = LoggerFactory.getLogger(AppServer.class);

    public static void main(String[] args) throws Exception {


        String portDef = System.getenv("PORT");
        Integer port = portDef != null ? Integer.valueOf(portDef) : 8080;
        _logger.info("------------------------PORT = '{}'-----------------------", port);
        Server server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(ExecutionServlet.class, "/*");
        server.start();

    }
}
