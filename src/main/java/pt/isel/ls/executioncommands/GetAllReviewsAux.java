package pt.isel.ls.executioncommands;

import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.exceptions.ApplicationException;
import pt.isel.ls.http.ExecutionServlet;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Review;
import pt.isel.ls.printers.PrintBody;
import pt.isel.ls.printers.Printable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

import static pt.isel.ls.executioncommands.AccessUtils.setValuesOnPreparedStatement;

/**
 * @author Tede Morgado
 *         Created at 25/05/2016
 */
public class GetAllReviewsAux implements CommandExecution {
    @Override
    public Printable execute(Connection connection, Command command) throws SQLException, ApplicationException {
        String sql = "Select * from Review where MovieID = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        setValuesOnPreparedStatement(ps, command.getPath().getPathInt("mid"));
        ResultSet rs = ps.executeQuery();
        Collection<Review> res = new ArrayList<Review>();
        while (rs.next()) {
            res.add(new Review(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(6)
            ));
        }
        if(res.isEmpty())
            return new PrintBody();
        String[] head =
                {"Review ID", "Reviewer Name", "Review Rating", "Summary Review"};
        ArrayList<Function<Review, String>> function = new ArrayList<>();
        function.add(review -> "" + review.getReviewID());
        function.add(review -> review.getReviewName());
        function.add(review -> "" + review.getReviewRating());
        function.add(review -> review.getReviewSummary());
        ArrayList<String> uri = new ArrayList<>();
        res.forEach(x -> uri.add("/movies/"+x.getMovieID()+"/reviews/"+x.getReviewID()));
        return new PrintBody(res, head, function, uri);
    }
}
