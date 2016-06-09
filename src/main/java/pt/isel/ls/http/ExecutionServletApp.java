package pt.isel.ls.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * @author Tede Morgado
 *         Created at 09/06/2016
 */
public class ExecutionServletApp
{
    private static Server server;

    public static void main(String[] args) throws Exception {
        String portDef = System.getenv("PORT");
        int port = portDef != null ? Integer.valueOf(portDef) : 8080;
        server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(ExecutionServlet.class, "/*");
        server.start();
    }
}
