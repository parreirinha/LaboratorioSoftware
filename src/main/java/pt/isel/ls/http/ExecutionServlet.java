package pt.isel.ls.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.executioncommands.CommandExecution;
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
import java.util.HashMap;
import java.util.Map;

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

    private static final Logger _logger = LoggerFactory.getLogger(ExecutionServlet.class);


    protected void doMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        port = req.getLocalPort();
        _logger.info("{} on '{}' with accept:'{}'", req.getMethod(), req.getRequestURI(), req.getHeader("Accept"));

        PrintWriter out;
        String str = "";
        if (req.getMethod().equals("POST")) {
            Map<String, String> map = getMap(req);
            for (int i = 0; i < post.length; ++i) {
                if (map.containsKey(post[i])) {
                    if (!str.equals(""))
                        str += "&";
                    str += post[i] + "=" + map.get(post[i]);
                }
            }
        }

        Command c = new UriCommandGetter().getCommandFromUri(req.getMethod(), req.getRequestURI(),
                req.getMethod().equals("GET") ? req.getQueryString() : str);
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
                if (req.getMethod().equals("POST")) {
                    resp.setStatus(303);
                    resp.setHeader("Location", c.getLocation());
                } else {
                    resp.setStatus(200);
                }
                resp.setContentType(identifyContentType(c));
                out = resp.getWriter();
                out.println(identifyOutputFormat(c, p));
                out.close();
            } catch (SQLException e) {
                _logger.info("Error '{}'", e.toString());
                resp.setStatus(500);
            } catch (ApplicationException e) {
                _logger.info("Error '{}'", e.toString());
                resp.sendError(400);
            }

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doMethod(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doMethod(req, resp);
    }

    private String[] post = {"name", "description", "mid", "title", "releaseYear", "rating", "reviewerName", "reviewSummary", "review"};

    private Map<String, String> getMap(HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < post.length; ++i) {
            String value = req.getParameter(post[i]);
            if (value != null)
                map.put(post[i], value);
        }
        return map;
    }

}
