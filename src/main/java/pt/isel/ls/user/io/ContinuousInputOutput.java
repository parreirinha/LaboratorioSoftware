package pt.isel.ls.user.io;

import pt.isel.ls.exceptions.ApplicationException;

import java.util.Scanner;

/**
 * Class used to continuously execute the application.
 */
public class ContinuousInputOutput {
    private Run run;
    private Scanner input;

    public void start() throws ApplicationException {
        run = new Run();
        input = new Scanner(System.in);

        while (input.hasNextLine()) {
            run.RunApp(input.nextLine().split(" "));
        }
    }

}
