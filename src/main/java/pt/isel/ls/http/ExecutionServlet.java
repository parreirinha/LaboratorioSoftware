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
 * Class used to execute an HTTP GET or POST request to the application and
 * that returns the correspondent response.
 */
public class ExecutionServlet extends HttpServlet {

    private static final Logger _logger = LoggerFactory.getLogger(ExecutionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doMethod(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doMethod(req, resp);
    }

    private void doMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        _logger.info("{} on '{}' with accept:'{}'", req.getMethod(), req.getRequestURI(), req.getHeader("Accept"));

        Command c = new UriCommandGetter().getCommandFromUri(req.getMethod(), req.getRequestURI(),
                req.getMethod().equals("GET") ? req.getQueryString() : buildPostParams(req));
        CommandExecution ce = new CommandMapper().getExecutionCommandInstance(c);

        servletExecute(req, resp, c, ce);
    }

    private void servletExecute(HttpServletRequest req, HttpServletResponse resp, Command c, CommandExecution ce) throws IOException {
        if (ce == null) {
            resp.sendError(400);
        } else {
            try {
                Printable p = ce.execute(
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
                finalize(resp, c, p);
            } catch (SQLException e) {
                _logger.info("Error '{}'", e.toString());
                resp.setStatus(500);
            } catch (ApplicationException e) {
                _logger.info("Error '{}'", e.toString());
                resp.sendError(400);
            }

        }
    }

    private void finalize(HttpServletResponse resp, Command c, Printable p) throws IOException {
        resp.setContentType(identifyContentType(c));
        PrintWriter out = resp.getWriter();
        out.println(identifyOutputFormat(c, p));
        out.close();
    }

    private String identifyContentType(Command c) {
        String s = c.getHeaders().getHeadersString("accept");

        if (s == null || s.equals("text/html")) {
            return "text/html";
        } else {
            return "text/plain";
        }
    }

    private String buildPostParams(HttpServletRequest req) {
        String[] s = {""};
        req.getParameterMap().forEach((k, v) -> {
            if (!s[0].equals(""))
                s[0] += "&";
            s[0] += k + "=" + v[0];
        });

        return s[0];
    }

}
