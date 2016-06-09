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
    private static final Logger _logger = LoggerFactory.getLogger(AppServer.class);
    public static void main(String[] args) throws Exception {


        //System.setProperty("org.slf4j.simpleLogger.levelInBrackets","true");
        _logger.info("------------------------PORT = '{}'-----------------------", System.getenv("PORT"));
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        //context.addServlet(new ServletHolder(new TimeServlet()),"/*");
        context.addServlet(new ServletHolder(new ExecutionServlet()),"/*");
        server.start();
        server.join();
        /*String portDef = System.getenv("PORT");
        int port = portDef != null ? Integer.valueOf(portDef) : 8080;
    	Server server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(new ServletHolder(new ExecutionServlet()), "/");
        server.start();
        server.join();*/

    }
}
