package pt.isel.ls.user.io;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.http.ExecutionServlet;
import static java.lang.System.getenv;
/**
 * @author Tede Morgado
 *         Created at 09/06/2016
 */
public class AppServer
{
    /*
    private static Server server = null;
    private static final Logger _logger = LoggerFactory.getLogger(AppServer.class);
    public static void main(String[] args) throws Exception {

        _logger.info("      LOG PORT = {}   ", Integer.valueOf(getenv("PORT")));
        Server server = new Server(Integer.valueOf(getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        server.start();
        server.join();
    }*/
}
