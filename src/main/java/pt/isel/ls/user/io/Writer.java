package pt.isel.ls.user.io;

import pt.isel.ls.exceptions.ApplicationException;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class whose writeToFile method is used to write a String
 * into a file with the given name.
 */
public class Writer {

    static private FileWriter fileWriter;

    public void writeToFile(String out, String filename) throws ApplicationException {
        createWriteFile(filename);

        try {
            fileWriter.write(out + '\r' + '\n');

        } catch (IOException e) {
            throw new ApplicationException();
        }

        closeWriteFile();
    }

    private void createWriteFile(String filename) throws ApplicationException{

        try {
            fileWriter = new FileWriter(filename);
        } catch (IOException e) {
            throw new ApplicationException();
        }
    }

    private void closeWriteFile() throws ApplicationException{

        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new ApplicationException();
        }
    }


}
