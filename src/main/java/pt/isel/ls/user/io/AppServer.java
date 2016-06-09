package pt.isel.ls.user.io;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import pt.isel.ls.http.ExecutionServlet;
import static java.lang.System.getenv;
/**
 * @author Tede Morgado
 *         Created at 09/06/2016
 */
public class AppServer
{
    private static Server server = null;
    public static void main(String[] args) throws Exception {
        server = new Server(Integer.parseInt(getenv("PORT")));
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(ExecutionServlet.class, "/*");
        server.start();
    }
}
