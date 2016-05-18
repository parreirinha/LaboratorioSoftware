package pt.isel.ls.http;

import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.linecommand.mapping.CommandMapper;
import pt.isel.ls.linecommand.model.Command;
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
 * Created by Dani on 17-05-2016.
 */
public class ExecutionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getRequestURI());
        PrintWriter out = resp.getWriter();
        Command c;

        c = new UriCommandGetter().getCommandFromUri("GET", req.getRequestURI(), req.getQueryString());

        Printable p = null;
        try {
            p = new CommandMapper().getExecutionCommandInstance(c).execute(
                    new ConnectionFactory().getNewConnection(), c);
        } catch (SQLException e) {
            e.printStackTrace();//TODO tratar estas excepçoes
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        resp.setContentType(identifyOutputFormat(c, p));

        out.println(p.toStringHtml());
    }
}
