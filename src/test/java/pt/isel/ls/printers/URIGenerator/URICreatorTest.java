package pt.isel.ls.printers.URIGenerator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static pt.isel.ls.printers.URIGenerator.URICreator.getURI;


public class URICreatorTest {

    @Test
    public void uriTest(){

        String uri = getURI("/collections", "skip=0&top=5", 8080, "Collections");
        String expected = "<a href=http://localhost:8080/collections?skip=0&top=5/>Collections</a>";
        assertEquals(expected, uri);

        uri = getURI("/movies", "skip=0&top=5", 8080, "Movies");
        expected = "<a href=http://localhost:8080/movies?skip=0&top=5/>Movies</a>";
        assertEquals(expected, uri);

        uri = getURI("/tops/ratings","skip=5&top=5",8080, "Top Ratings");
        expected = "<a href=http://localhost:8080/tops/ratings?skip=5&top=5/>Top Ratings</a>";
        assertEquals(expected, uri);
    }


}
