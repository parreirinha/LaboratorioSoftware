package pt.isel.ls.html.output;


import java.lang.reflect.Array;
import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Dani on 12-04-2016.
 */
public class MovieHtmlGenerator<T> {

    private Collection<T> col;

    public <T> MovieHtmlGenerator(Collection<T> col){
       //this.col = col;
    }

    public String htmlGenerate(String[] head, Function<T, String>[] func)
    {
        String str = "";
        return null;
    }


    //public java.lang.String htmlGenerate(){

        /*
        java.lang.String htmlMovieString = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Movie</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h3>Title:</h3>\n" +
                "<p>"+movie.getMovieName()+"</p>\n" +
                "\n" +
                "<h3>Release Year:</h3>\n" +
                "<p>"+movie.getMovieRelease()+"</p>\n" +
                "\n" +
                "<h3>Movie ID:</h3>\n" +
                "<p>"+movie.getMovieID()+"</p>\n" +
                "\n" +
                "<h3>*:</h3>\n" +
                "<p>"+movie.getOneStar()+"</p>\n" +
                "\n" +
                "<h3>**:</h3>\n" +
                "<p>"+movie.getTwoStar()+"</p>\n" +
                "\n" +
                "<h3>***:</h3>\n" +
                "<p>"+movie.getThreeStar()+"</p>\n" +
                "\n" +
                "<h3>****:</h3>\n" +
                "<p>"+movie.getFourStar()+"</p>\n" +
                "\n" +
                "<h3>*****:</h3>\n" +
                "<p>"+movie.getFiveStar()+"</p>\n" +
                "\n" +
                "<h3>Average:</h3>\n" +
                "<p>"+movie.getAverage()+"</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
*/
      //  return htmlMovieString;
   // }
}
