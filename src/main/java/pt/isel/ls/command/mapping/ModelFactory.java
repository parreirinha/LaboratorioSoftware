package pt.isel.ls.command.mapping;

import pt.isel.ls.command.model.Command;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Rating;
import pt.isel.ls.model.Review;

import java.util.ArrayList;

/**
 * Class used to produce models from Command instances.
 */
public class ModelFactory {

    public Object getModel(Command command){
        Object obj = null;
        int commandIndex = new CommandMapper().getCommandIndex(command);

        switch (commandIndex) {
            case 0: obj = new Movie(command.getParams().getParamParts().iterator().next(),
                    command.getParams().getIntegers().iterator().next()); break;

            case 2: obj = command.getPath().getIntegers().iterator().next(); break;

            case 3: obj = new Rating(command.getParams().getIntegers().iterator().next(),
                    command.getPath().getIntegers().iterator().next()); break;

            case 4: obj = command.getPath().getIntegers().iterator().next(); break;

            case 5: ArrayList<String> array = (ArrayList<String>)command.getParams().getParamParts();
                    //TODO - scope do construtor
                    obj = new Review(array.get(0), array.get(1),array.get(2),
                            command.getParams().getIntegers().iterator().next(),
                    command.getPath().getIntegers().iterator().next()
                    ); break;

            case 6: obj = command.getPath().getIntegers().iterator().next(); break;

            case 7: obj = command.getPath().getIntegers(); break;

            case 9: obj = command.getPath().getIntegers().iterator().next(); break;

            case 11: obj = command.getPath().getIntegers().iterator().next(); break;

            case 13: obj = command.getPath().getIntegers().iterator().next(); break;


        }

        return obj;
    }
}
