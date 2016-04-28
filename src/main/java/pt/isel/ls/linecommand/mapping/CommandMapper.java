package pt.isel.ls.linecommand.mapping;

;

import pt.isel.ls.executioncommands.*;
import pt.isel.ls.linecommand.model.Command;


import java.util.HashMap;

/**
 * Class used to identify and return the application method to
 * execute, given a Command instance, using a HashMap<String, Commands> structure.
 */
public class CommandMapper {
    private HashMap<String, CommandExecution> commmandMap;

    public CommandMapper(){
        initMap();
    }

    private void initMap(){
        commmandMap = new HashMap<String, CommandExecution>();
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
        commmandMap.put("POSTcollections", new PostCollection());
        commmandMap.put("GETcollections", new GetCollections());
        commmandMap.put("GETcollectionscid", new GetCollectionById());
        commmandMap.put("POSTcollectionscidmovies", new PostMovieInCollection());
        commmandMap.put("DELETEcollectionscidmoviesmid", new DeleteMovieFromCollection());
        commmandMap.put("OPTION", new Option());
        commmandMap.put("EXIT", new Exit());
        commmandMap.put("", new InteractiveMode());
    }


    public CommandExecution getExecutionCommandInstance(Command command){

        String commandProcessedString = command.getMethod().getMethod() + command.getPath().getPathString();

        if(!commmandMap.containsKey(commandProcessedString)){
            System.out.println("Error: Invalid Command.");
            return commmandMap.get("EXIT");
        }

        return commmandMap.get(commandProcessedString);
    }


}
