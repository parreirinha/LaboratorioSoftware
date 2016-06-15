package pt.isel.ls.executioncommands;

import org.junit.Test;
import pt.isel.ls.printers.PrintOption;

import java.sql.SQLException;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Class used to test Option command.
 */
public class OptionTests {
    private final String expectedString = "\n" +
            "Available Commands:\n" +
            "\n" +
            " POST /movies  - creates a new movie, given the following parameters\n" +
            "  title  - movie name.\n" +
            "  releaseYear  - movie's release year.\n" +
            "\n" +
            "\n" +
            "This command returns the movie unique identifier.\n" +
            "\n" +
            "\n" +
            " GET /movies  - returns a list with all movies.\n" +
            "\n" +
            "\n" +
            " GET /movies/{mid}  - returns the detailed information for the movie identified by  mid .\n" +
            "\n" +
            "\n" +
            " POST /movies/{mid}/ratings  - submits a new rating for the movie identified by  mid , given the following parameters\n" +
            "  rating  - integer between 1 and 5.\n" +
            "\n" +
            "\n" +
            " GET /movies/{mid}/ratings  - returns the rating information for the movie identified by  mid . This rating information include:\n" +
            "The rating average\n" +
            "The number of votes for each rating value\n" +
            "\n" +
            "\n" +
            " POST /movies/{mid}/reviews  - creates a new review for the movie identified by  mid , given the following parameters\n" +
            "  reviewerName  - the reviewer name \n" +
            "  reviewSummary  - the review summary\n" +
            "  review  - the complete review\n" +
            "  rating  - the review rating\n" +
            "\n" +
            "This command returns the review unique identifier.\n" +
            "\n" +
            "\n" +
            " GET /movies/{mid}/reviews  - returns all the reviews for the movie identified by  mid . The information for each review must not include the full review text.\n" +
            "\n" +
            "\n" +
            " GET /movies/{mid}/reviews/{rid}  - returns the full information for the review  rid  of the movie identified by  mid .\n" +
            "\n" +
            "\n" +
            " GET /tops/ratings/higher/average  - returns the detail for the movie with the higher average rating.\n" +
            "\n" +
            "\n" +
            " GET /tops/{n}/ratings/higher/average  - returns a list with the  n  movies with higher average ratings, sorted decreasingly.\n" +
            "\n" +
            "\n" +
            " GET /tops/ratings/lower/average  - returns the detail for the movie with the lower average rating.\n" +
            "\n" +
            "\n" +
            " GET /tops/{n}/ratings/lower/average  - returns a list with the  n  movies with the lower average ratings, sorted decreasingly.\n" +
            "\n" +
            "\n" +
            " GET /tops/reviews/higher/count  - returns the detail for the movie with most reviews.\n" +
            "\n" +
            "\n" +
            " GET /tops/{n}/reviews/higher/count  - returns a list with the  n  movies with higher review count.\n" +
            "\n" +
            "\n" +
            " POST /collections  - creates a new collection and returns its identifier, given the following parameters\n" +
            "  name  - the tag unique name;\n" +
            "  description  - the tag description;\n" +
            "\n" +
            "\n" +
            " GET /collections  - returns the list of collections, using the insertion order.\n" +
            "\n" +
            "\n" +
            " GET /collections/{cid}  - returns the details for the  cid  collection, namely all the movies in that collection.\n" +
            "\n" +
            "\n" +
            " POST /collections/{cid}/movies/  - adds a movie to the  cid  collection, given\n" +
            "  mid  - the movie unique identifier.\n" +
            "\n" +
            "\n" +
            " DELETE /collections/{cid}/movies/{mid}  - removes the movie  mid  from the collections  cid .\n" +
            "\n" +
            "\n" +
            " OPTION /  - presents a list of available commands and their characteristics.\n" +
            "\n" +
            "\n" +
            " EXIT /  - ends the application.\n" +
            "\n";

    @Test
    public void shouldTestOptionCommand() throws SQLException {
        try {
            assertEquals(expectedString, ((PrintOption) new Option().execute(null, null)).toStringText());
        } catch (pt.isel.ls.exceptions.ApplicationException e) {
            e.printStackTrace();
        }
    }


}
