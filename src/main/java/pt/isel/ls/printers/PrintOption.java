package pt.isel.ls.printers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Dani on 26-04-2016.
 */
public class PrintOption implements Printable {
    Scanner input;

    public PrintOption(String fileName) {
        input = tryOpenFile(input, fileName);
    }

    @Override
    public String toStringText() {
        String s ="";
        while(input.hasNextLine()){
            s += input.nextLine()+"\n";
        }
        input.close();
        return s;
    }

    @Override
    public String toStringHtml() {
        return toStringText();
    }

    private Scanner tryOpenFile(Scanner in, String fileName){
        try {
            in = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return in;
    }

}
