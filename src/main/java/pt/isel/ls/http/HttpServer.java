package pt.isel.ls.http;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Created by Dani on 17-05-2016.
 */
public class HttpServer {
    private static final int LISTEN_PORT = 8090;

    public static void main(String[] args) throws Exception {

        Server server = new Server(LISTEN_PORT);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(ExampleServlet.class, "/*");
        server.start();
        System.out.println("Server is started");

        server.join();
        System.out.println("Server is stopped, bye");
    }
}
