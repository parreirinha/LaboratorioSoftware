package pt.isel.ls.user.io;

import org.junit.After;
import org.junit.Test;
import pt.isel.ls.exceptions.ApplicationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class used to test Writer.
 */
public class WriterTests {

    private final String TestOneString = "This is a software test.";
    private final String TestMoreThanOneString = "This is a software test.\nThis is second line String.\n";
    private final String TestFileName = "testfile";

    @After
    public void cleanFile() {
        new File(TestFileName).delete();
    }

    @Test
    public void shouldReturnSameWritenString() throws FileNotFoundException, ApplicationException {
        new Writer().writeToFile(TestOneString, TestFileName);
        Scanner in = new Scanner(new FileReader(TestFileName));
        String res = in.nextLine();
        in.close();
        assertEquals(TestOneString, res);
    }

    @Test
    public void shouldReturnSameWritenStrings() throws FileNotFoundException, ApplicationException {
        new Writer().writeToFile(TestMoreThanOneString, TestFileName);
        Scanner in = new Scanner(new FileReader(TestFileName));
        String res1 = in.nextLine();
        String res2 = in.nextLine();
        in.close();
        assertEquals(TestOneString, res1);
        assertEquals("This is second line String.", res2);
    }

}
