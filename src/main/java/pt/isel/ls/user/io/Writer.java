package pt.isel.ls.user.io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class whose writeToFile method is used to write a String
 * into a file with the given name.
 */
public class Writer {

    static private FileWriter fileWriter;

    public void writeToFile(String out, String filename) {
        createWriteFile(filename);

        try {
            fileWriter.write(out + '\r' + '\n');

        } catch (IOException e) {
            e.printStackTrace();
        }

        closeWriteFile();
    }

    private void createWriteFile(String filename) {

        try {
            fileWriter = new FileWriter(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeWriteFile() {

        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
