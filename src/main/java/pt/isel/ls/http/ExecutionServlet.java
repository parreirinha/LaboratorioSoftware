package pt.isel.ls.http;

import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.executioncommands.HttpHomePage;
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
 * Class used to execute an HTTP GET request to the application and
 * that returns the correspondent response.
 */
public class ExecutionServlet extends HttpServlet {

    public static int getPort() {
        return port;
    }

    private static int port;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        port = req.getLocalPort();

        PrintWriter out = resp.getWriter();
        Command c;

        c = new UriCommandGetter().getCommandFromUri("GET", req.getRequestURI(), req.getQueryString());

        Printable p = null;
        try {
            p = new CommandMapper().getExecutionCommandInstance(c).execute(
                    new ConnectionFactory().getNewConnection(), c);
        } catch (SQLException e) {
            e.printStackTrace();//TODO tratar estas excepçoes e implementar lançamentod de erros http
        } catch (ApplicationException e) {
            e.printStackTrace();
        }

        resp.setContentType(identifyContentType(c));

        out.println(identifyOutputFormat(c,p));
    }

    private String identifyContentType(Command c){
        String s = c.getHeaders().getHeadersString("accept");

        if(s==null || s.equals("text/html")){
            return "text/html";
        }else{
            return "text/plain";
        }

    }
}
