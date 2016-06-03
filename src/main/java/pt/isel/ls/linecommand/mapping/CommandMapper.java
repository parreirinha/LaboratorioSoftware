package pt.isel.ls.linecommand.mapping;


import pt.isel.ls.executioncommands.*;
import pt.isel.ls.linecommand.model.Command;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class used to identify and return the application method to
 * execute, given a Command instance, using a HashMap<String, Commands> structure.
 */
public class CommandMapper {

    private HashMap<String, CommandExecution> commmandMap;

    public CommandMapper() {
        initMap();
    }

    private void initMap() {
        commmandMap = new HashMap<String, CommandExecution>();
        commmandMap.put("POST/movies", new PostMovie());
        commmandMap.put("GET/movies", new GetAllMovies());
        commmandMap.put("GET/movies/{mid}", new GetMovie());
        commmandMap.put("POST/movies/{mid}/ratings", new PostMovieRating());
        commmandMap.put("GET/movies/{mid}/ratings", new GetMovieRating());
        commmandMap.put("POST/movies/{mid}/reviews", new PostMovieReview());
        commmandMap.put("GET/movies/{mid}/reviews", new GetAllReviews());
        commmandMap.put("GET/movies/{mid}/reviews/{rid}", new GetReviewById());
        commmandMap.put("GET/tops/ratings/higher/average", new GetTopRatingsHigherAverage());
        commmandMap.put("GET/tops/{n}/ratings/higher/average", new GetTopNRatingsHigherAverage());
        commmandMap.put("GET/tops/ratings/lower/average", new GetTopRatingsLowerAverage());
        commmandMap.put("GET/tops/{n}/ratings/lower/average", new GetTopNRatingsLowerAverage());
        commmandMap.put("GET/tops/reviews/higher/count", new GetTopMovieWithHigherReviewCount());
        commmandMap.put("GET/tops/{n}/reviews/higher/count", new GetTopNMoviesWithHigherReviewCount());
        commmandMap.put("POST/collections", new PostCollection());
        commmandMap.put("GET/collections", new GetCollections());
        commmandMap.put("GET/collections/{cid}", new GetCollectionById());
        commmandMap.put("POST/collections/{cid}/movies", new PostMovieInCollection());
        commmandMap.put("DELETE/collections/{cid}/movies/{mid}", new DeleteMovieFromCollection());
        commmandMap.put("OPTION/", new Option());
        commmandMap.put("EXIT/", new Exit());
        commmandMap.put("", new InteractiveMode());
        commmandMap.put("LISTEN/", new Listen());
        commmandMap.put("GET", new HttpHomePage());
        commmandMap.put("GET/tops/ratings", new TopsRatings());
        commmandMap.put("GET/tops/{n}/reviews/lower/count", new GetTopNMoviesWithLowerReviewCount());
    }


    public CommandExecution getExecutionCommandInstance(Command command) {

        String commandProcessedString = command.getMethod().getMethod() + command.getPath().getPathString();
        if (!commmandMap.containsKey(commandProcessedString)) {
            System.out.println("Error: Invalid Command.");
            return null;
        }

        return commmandMap.get(commandProcessedString);
    }

    public ArrayList<String> getCommmandMapKeys() {
        ArrayList<String> keys = new ArrayList<>();
        commmandMap.forEach((s, ce) -> {
            keys.add(removeMethodName(s));
        });

        return keys;
    }

    private String removeMethodName(String s) {
        int i = 0, size = s.length();
        for (; i < size; ++i) {
            char c = s.charAt(i);
            if(c <'A' || c > 'Z')
                break;
        }
        return i == 0 ? "" : s.substring(i);
    }


}
