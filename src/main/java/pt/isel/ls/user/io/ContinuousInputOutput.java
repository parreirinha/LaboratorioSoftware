package pt.isel.ls.user.io;

import java.util.Scanner;

/**
 * Created by Dani on 14-04-2016.
 */
public class ContinuousInputOutput {
    private Run run;
    private Scanner input;

    public void start(){
        run = new Run();
        input = new Scanner(System.in);

        while(true){
            run.RunApp(input.nextLine().split(" "));
        }
    }

}
