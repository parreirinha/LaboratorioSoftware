package pt.isel.ls.command.mapping;

import pt.isel.ls.command.model.Command;

/**
 * Class used to identify and return the application method to
 * execute, given a Command instance.
 */
public class CommandMapper {

    private String[] commandTemplates = {
            "POSTmovies",
            "GETmovies",
            "GETmoviesmid",
            "POSTmoviesmidratings",
            "GETmoviesmidratings",
            "POSTmoviesmidreviews",
            "GETmoviesmidreviews",
            "GETmoviesmidreviewsrid",
            "GETtopsratingshigheraverage",
            "GETtopsnratingshigheraverage",
            "GETtopsratingsloweraverage",
            "GETtopsnratingsloweraverage",
            "GETtopsreviewshighercount",
            "GETtopsnreviewshighercount",
    };

    public int getCommandIndex(Command command){
        int commandIndex=0;

        String commandProcessedString = command.getMethod().getMethod() + command.getPath().getPathString();

        for( ;commandIndex < commandTemplates.length; ++commandIndex)
            if(commandProcessedString.equals(commandTemplates[commandIndex]))
                break;

        return commandIndex;
    }


}
