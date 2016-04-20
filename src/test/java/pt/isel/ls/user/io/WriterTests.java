package pt.isel.ls.user.io;

import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dani on 19-04-2016.
 */
public class WriterTests {

    private final String TestString = "This is a software test.";
    private final String TestFileName = "testfile";

    @After
    public void cleanFile(){
        new File(TestFileName).delete();
    }

    @Test
    public void shouldReturnSameWritenString() throws FileNotFoundException {
        new Writer().writeToFile(TestString, TestFileName);
        Scanner in = new Scanner(new FileReader(TestFileName));
        String res = in.nextLine();
        in.close();
        assertEquals(TestString, res);
    }


}
