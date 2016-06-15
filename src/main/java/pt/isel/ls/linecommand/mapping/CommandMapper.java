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

    private HashMap<String, CommandExecution> commandMap;

    public CommandMapper() {
        initMap();
    }

    private void initMap() {
        commandMap = new HashMap<String, CommandExecution>();
        commandMap.put("POST/movies", new PostMovie());
        commandMap.put("GET/movies", new GetAllMovies());
        commandMap.put("GET/movies/{mid}", new GetMovie());
        commandMap.put("POST/movies/{mid}/ratings", new PostMovieRating());
        commandMap.put("GET/movies/{mid}/ratings", new GetMovieRating());
        commandMap.put("POST/movies/{mid}/reviews", new PostMovieReview());
        commandMap.put("GET/movies/{mid}/reviews", new GetAllReviews());
        commandMap.put("GET/movies/{mid}/reviews/{rid}", new GetReviewById());
        commandMap.put("GET/tops/ratings/higher/average", new GetTopRatingsHigherAverage());
        commandMap.put("GET/tops/{n}/ratings/higher/average", new GetTopNRatingsHigherAverage());
        commandMap.put("GET/tops/ratings/lower/average", new GetTopRatingsLowerAverage());
        commandMap.put("GET/tops/{n}/ratings/lower/average", new GetTopNRatingsLowerAverage());
        commandMap.put("GET/tops/reviews/higher/count", new GetTopMovieWithHigherReviewCount());
        commandMap.put("GET/tops/{n}/reviews/higher/count", new GetTopNMoviesWithHigherReviewCount());
        commandMap.put("POST/collections", new PostCollection());
        commandMap.put("GET/collections", new GetCollections());
        commandMap.put("GET/collections/{cid}", new GetCollectionById());
        commandMap.put("POST/collections/{cid}/movies", new PostMovieInCollection());
        commandMap.put("DELETE/collections/{cid}/movies/{mid}", new DeleteMovieFromCollection());
        commandMap.put("OPTION/", new Option());
        commandMap.put("EXIT/", new Exit());
        commandMap.put("", new InteractiveMode());
        commandMap.put("LISTEN/", new Listen());
        commandMap.put("GET/", new HttpHomePage());
        commandMap.put("GET/tops/ratings", new TopsRatings());
        commandMap.put("GET/tops/{n}/reviews/lower/count", new GetTopNMoviesWithLowerReviewCount());
    }


    public CommandExecution getExecutionCommandInstance(Command command) {

        String commandProcessedString = command.getMethod().getMethod() + command.getPath().getPathString();
        if (!commandMap.containsKey(commandProcessedString)) {
            //System.out.println("Error: Invalid Command.");
            return null;
        }

        return commandMap.get(commandProcessedString);
    }

    public ArrayList<String> getCommandMapKeys() {
        ArrayList<String> keys = new ArrayList<>();
        commandMap.forEach((s, ce) -> {
            keys.add(removeMethodName(s));
        });

        return keys;
    }

    private String removeMethodName(String s) {
        int i = 0, size = s.length();
        for (; i < size; ++i) {
            char c = s.charAt(i);
            if (c < 'A' || c > 'Z')
                break;
        }
        return i == 0 ? "" : s.substring(i);
    }


}
