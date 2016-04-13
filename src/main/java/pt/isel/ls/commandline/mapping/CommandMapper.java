package pt.isel.ls.command.mapping;

import pt.isel.ls.command.model.Command;
import pt.isel.ls.database.access.*;

import java.util.HashMap;

/**
 * Class used to identify and return the application method to
 * execute, given a Command instance, using a HashMap<String, Commands> structure.
 */
public class CommandMapper {
    private HashMap<String, Commands> commmandMap;

    public CommandMapper(){
        initMap();
    }

    private void initMap(){
        commmandMap = new HashMap<String, Commands>();
        commmandMap.put("POSTmovies", new PostMovie());
        commmandMap.put("GETmovies", new GetAllMovies());
        commmandMap.put("GETmoviesmid", new GetMovie());
        commmandMap.put("POSTmoviesmidratings", new PostMovieRating());
        commmandMap.put("GETmoviesmidratings", new GetMovieRating());
        commmandMap.put("POSTmoviesmidreviews", new PostMovieReview());
        commmandMap.put("GETmoviesmidreviews", new GetAllReviews());
        commmandMap.put("GETmoviesmidreviewsrid", new GetReviewById());
        commmandMap.put("GETtopsratingshigheraverage", new GetTopRatingsHigherAverage());
        commmandMap.put("GETtopsnratingshigheraverage",new GetTopsNRatingsHigherAverage());
        commmandMap.put("GETtopsratingsloweraverage", new GetTopRatingsLowerAverage());
        commmandMap.put("GETtopsnratingsloweraverage", new GetTopNRatingsLowerAverage());
        commmandMap.put("GETtopsreviewshighercount", new GetTopMovieWithHigherReviewCount());
        commmandMap.put("GETtopsnreviewshighercount", new GetTopNMoviesWithHigherReviewCount());
        commmandMap.put("POSTcollections", null);
        commmandMap.put("GETcollections", null);
        commmandMap.put("GETcollectionscid", null);
        commmandMap.put("POSTcollectionscidmovie", null);
        commmandMap.put("DELETEcollectionscidmoviesmid", null);
        commmandMap.put("OPTION", null);
        commmandMap.put("EXIT", null);
        commmandMap.put("", null);
    }


    public Commands getExecutionCommandInstance(Command command){

        String commandProcessedString = command.getMethod().getMethod() + command.getPath().getPathString();

        if(!commmandMap.containsKey(commandProcessedString)){
            System.out.println("Error: Invalid Command.");
            return null;
        }

        return commmandMap.get(commandProcessedString);
    }


}
