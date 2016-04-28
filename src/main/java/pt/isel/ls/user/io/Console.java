package pt.isel.ls.user.io;

import pt.isel.ls.exceptions.ApplicationException;

/**
 * Class used as entry point to the application.
 */
public class Console {

    public static void main(String[] args) {
        try {
            new Run().RunApp(args);
        } catch (ApplicationException e) {
            System.out.println("There was an application exception. Application will finish.");
            System.exit(-1);
        }
    }
}
