package pt.isel.ls.database.access;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import pt.isel.ls.database.connection.ConnectionFactory;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class responsable to create data for all tests
 */
public class DataCreationTests {

    Connection connection = null;
    private PreparedStatement preparedStatement = null;

    // Arrays to compare values in the assertEquals methods
    public final Movie[] movies = {
            new Movie(1,"Fight Club", 1999, 20,10,15,50,32),
            new Movie(2,"Seven",1995,5,20,40,35,22),
            new Movie(3,"The Matrix",1999, 33,14,70,15,1),
            new Movie(4,"Inception",2010,0,5,30,44,60),
            new Movie(5,"Pulp Fiction",1994,30,8,34,13,20),
            new Movie(6,"American History X",1998,1,5,20,100,50),
            new Movie(7,"The Silence of the Lambs",1991,2,30,11,40,22),
            new Movie(8, "PostMovieTest", 2016,0,0,0,0,0)
       };
    public final Review[] reviews= {
            new Review(1,1,"Manel","Magnificent","An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soap maker, forming an underground fight club that evolves into something much, much more...",5),
            new Review(2,1,"Bad taste Reviwer","Horrible","This is the worst movie i have seen in my life",1),
            new Review(3,6,"Donald Trump","Love it","I love skinheads, but I still prefer my toupee",5),
            new Review(4,2,"Jack","Morgan Freeman is the best","Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his modus operandi",2),
            new Review(5,3,"IT guy","interesting concept", "In a futuristic world some heroes battles the forces of evil in a cybernetic world",3),
            new Review(6,4,"Manel","Film of the year candidate","Interesting concept movie about dreams hunters",5)
    };


    public void insertMoviesToTest() throws SQLException {
        String query =
            "insert into Movie (MovieName, MovieRelease, OneStar, TwoStar, TreeStar, FourStar, FiveStar) " +
            "select 'Fight Club', 1999, 20, 10, 15, 50, 32 union all " +
            "select 'Seven', 1995, 5, 20, 40, 35, 22 union all " +
            "select 'The Matrix', 1999, 33, 14, 70, 15, 1 union all " +
            "select 'Inception', 2010, 0, 5, 30, 44, 60 union all " +
            "select 'Pulp Fiction', 1994, 30, 8, 34, 13, 20 union all " +
            "select 'American History X', 1998, 1, 5, 20, 100, 50  union all " +
            "select 'The Silence of the Lambs', 1991, 2, 30, 11, 40,22;";
        connection = new ConnectionFactory().connectionFactory();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        if (connection != null)
            connection.close();
    }

    public void insertReviewsInMovies() throws SQLException {
        String query =
            "insert into Review (MovieID,ReviewerName, ReviewSummary,ReviewComplete,ReviewRating)" +
            "select 1, 'Manel', 'Magnificent', 'An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soap maker, forming an underground fight club that evolves into something much, much more... ',5 union all " +
            "select 1, 'Bad taste Reviwer', 'Horrible', 'This is the worst movie i have seen in my life',1 union all " +
            "select 6, 'Donald Trump', 'Love it', 'I love skinheads, but I still prefer my toupee', 5 union all " +
            "select 2, 'Jack', 'Morgan Freeman is the best', 'Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his modus operandi', 2 union all " +
            "select 3, 'IT guy', 'interesting concept', 'In a futuristic world some heroes battles the forces of evil in a cybernetic world',3 union all " +
            "select 4, 'Manel', 'Film of the year candidate', 'Interesting concept movie about dreams hunters',5;";
        connection = new ConnectionFactory().connectionFactory();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        if (connection != null)
            connection.close();
    }


    public void deleteAllReviews() throws SQLException {
        String query = "delete from Review where (ReviewID >= 0)";
        connection = new ConnectionFactory().connectionFactory();
        preparedStatement =connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        if (connection != null)
            connection.close();
    }
    public void deleteAllMovies() throws SQLException {
        String query = "delete from Movie where (MovieID >= 0)";
        connection = new ConnectionFactory().connectionFactory();
        preparedStatement =connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        if (connection != null)
            connection.close();
    }
}
