package pt.isel.ls.http;

import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.executioncommands.CommandExecution;
import pt.isel.ls.executioncommands.Exit;
import pt.isel.ls.executioncommands.HttpHomePage;
import pt.isel.ls.linecommand.mapping.CommandMapper;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.PrintError;
import pt.isel.ls.printers.Printable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static pt.isel.ls.user.io.Run.identifyOutputFormat;


/**
 * Class used to execute an HTTP GET request to the application and
 * that returns the correspondent response.
 */
public class ExecutionServlet extends HttpServlet {

    public static int getPort() {
        return port;
    }

    private static int port = 8000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        port = req.getLocalPort();

        PrintWriter out = resp.getWriter();
        Command c = new UriCommandGetter().getCommandFromUri("GET", req.getRequestURI(), req.getQueryString());

        CommandExecution ce = new CommandMapper().getExecutionCommandInstance(c);

        if (ce == null) {
            resp.sendError(400);
        } else {

            Printable p = null;
            try {
                p = ce.execute(
                        new ConnectionFactory().getNewConnection(), c);

                if (p instanceof PrintError) {
                    resp.sendError(400);
                }

                resp.setStatus(200);
            } catch (SQLException e) {
                resp.setStatus(500);
            } catch (ApplicationException e) {
                resp.sendError(400);
            }

            resp.setContentType(identifyContentType(c));

            out.println(identifyOutputFormat(c, p));
        }
    }

    private String identifyContentType(Command c) {
        String s = c.getHeaders().getHeadersString("accept");

        if (s == null || s.equals("text/html")) {
            return "text/html";
        } else {
            return "text/plain";
        }

    }
}
