package pt.isel.ls.user.io;

import pt.isel.ls.exceptions.ApplicationException;

import java.util.Scanner;

/**
 * Created by Dani on 14-04-2016.
 */
public class ContinuousInputOutput {
    private Run run;
    private Scanner input;

    public void start() throws ApplicationException {
        run = new Run();
        input = new Scanner(System.in);

        while(input.hasNextLine()){
            run.RunApp(input.nextLine().split(" "));
        }
    }

}
