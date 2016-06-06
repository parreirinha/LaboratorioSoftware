package pt.isel.ls.printers.html;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.printers.URIGenerator.URIUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

/**
 * @author Tede Morgado
 *         Created at 02/06/2016
 */
public class HtmlGenerator
{
    private final String template =
            "<!DOCTYPE>\n" +
            "\t<html>\n" +
            "\t\t<head>\n" +
            "\t\t\t<meta\n" +
            "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
            "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
            "\t\t\t/>" +
            "\n" +
            "\t\t</head>\n" +
            "\n" +
            "\t\t<body>\n" +
            "%s" +
            "\n" +
            "\t\t</body>\n" +
            "\t</html>",
        startTab = "\t\t\t";
    private String html = "";

    public HtmlGenerator() {}

    public HtmlGenerator createMenu(ArrayList<String> values)
    {
        html += startTab + "<table border=\"0\"  style=\"width:100%\">\n"+
                startTab + "\t<tr>\n";
        values.forEach(x -> html += startTab + "\t\t<td align=\"center\"><h3>"+ x + "</h3></td>\n");
        html += startTab + "\t</tr>\n" +
                startTab + "</table>\n";
        return this;
    }

    public HtmlGenerator postNewCollectionForm()
    {
        html += startTab + "Post Collection<br>"+
                startTab + "<form method=\"POST\" action=\"/collections?\">\n" +
                startTab + "\tName:<input name=\"name\" type=\"text\">\n" +
                startTab + "\t<br>\n" +
                startTab + "\tDescription:<input name=\"description\" type=\"text\">\n" +
                startTab + "\t<br>\n" +
                startTab + "\t<input type=\"submit\" value=\"Submit\">\n" +
                startTab + "\t<br>"+
                startTab + "</form>\n" +
                startTab + "<br>" +
                startTab + "<br>";
        return this;
    }

    public HtmlGenerator postMovieIntoCollection(int id)
    {
        html += startTab + "Post Movie into Collection\n" +
                startTab + "<br>\n" +
                startTab + "<form method=\"POST\" action=\"/collections/"+id+"/?\">\n" +
                startTab + "\tID:<input name=\"mid\" type=\"number\">\n" +
                startTab + "</form>\n" +
                startTab + "<br>\n" +
                startTab + "<br>\n";
        return this;
    }

    public HtmlGenerator postNewMovie()
    {
        html += startTab + "Post Movie\n" +
                startTab + "<br>\n"+
                startTab + "<form method=\"POST\" action=\"/movies\">\n" +
                startTab + "\tName:<input name=\"title\" type=\"text\">\n" +
                startTab + "\t<br>\n" +
                startTab + "\tYear:<input name=\"releaseYear\" type=\"number\">\n" +
                startTab + "\t<br>\n" +
                startTab + "\t<input type=\"submit\" value=\"Submit\">\n" +
                startTab + "</form>" +
                startTab + "<br>\n" +
                startTab + "<br>\n";
        return this;
    }

    public HtmlGenerator postRatingMovie(int id)
    {
        html += startTab + "Post Rating\n" +
                startTab + "<br>\n" +
                startTab + "<form method=\"POST\" action=\"/movies/"+id+"/ratings?\">\n" +
                startTab + "\tRating(1-5):\n" +
                startTab + "\t<br>\n" +
                startTab + "\t<input type=\"radio\" name=\"rating\" value=\"1\"> 1\n" +
                startTab + "\t<br>\n"+
                startTab + "\t<input type=\"radio\" name=\"rating\" value=\"2\"> 2\n" +
                startTab + "\t<br>\n"+
                startTab + "\t<input type=\"radio\" name=\"rating\" value=\"3\"> 3\n" +
                startTab + "\t<br>\n"+
                startTab + "\t<input type=\"radio\" name=\"rating\" value=\"4\"> 4\n" +
                startTab + "\t<br>\n"+
                startTab + "\t<input type=\"radio\" name=\"rating\" value=\"5\"> 5\n" +
                startTab + "\t<br>\n"+
                startTab + "</form>\n" +
                startTab + "<br>\n" +
                startTab + "<br>\n";
        return this;
    }

    public HtmlGenerator postNewReview(int id)
    {
        html += startTab + "Post Review\n" +
                startTab + "<br>\n" +
                startTab + "<form method=\"POST\" action=\"/movies/"+id+"/reviews?\">\n" +
                startTab + "\tName:<input name=\"reviewerName\" type=\"text\">\n" +
                startTab + "\t<br>" +
                startTab + "\tSummary:<input name=\"reviewSummary\" type=\"text\">\n" +
                startTab + "\t<br>\n" +
                startTab + "\tComplete review:<input name=\"review\" type=\"text\">\n" +
                startTab + "\t<br>\n" +
                startTab + "\tRating(1-5):\n" +
                startTab + "\t<br>" +
                startTab + "\t<input type=\"radio\" name=\"rating\" value=\"1\"> 1\n" +
                startTab + "\t<br>\n"+
                startTab + "\t<input type=\"radio\" name=\"rating\" value=\"2\"> 2\n" +
                startTab + "\t<br>\n"+
                startTab + "\t<input type=\"radio\" name=\"rating\" value=\"3\"> 3\n" +
                startTab + "\t<br>\n"+
                startTab + "\t<input type=\"radio\" name=\"rating\" value=\"4\"> 4\n" +
                startTab + "\t<br>\n"+
                startTab + "\t<input type=\"radio\" name=\"rating\" value=\"5\"> 5\n" +
                startTab + "\t<br>\n"+
                startTab + "\t<input type=\"submit\" value=\"Submit\">\n" +
                startTab + "\t<br>\n"+
                startTab + "\t</form>\n" +
                startTab + "\t<br>\n" +
                startTab + "\t<br>\n";
        return this;
    }

    public HtmlGenerator createPagging(Command command, String path)
    {
        String prevParams = URIUtils.getPreviusSkipAndTopValuesFromCommand(command);
        String nextParams = URIUtils.getNextSkipAndTopValuesFromCommand(command);

        html += startTab + "<table border=\"0\" style=\"width:100%\">\n"+
                startTab + "\t<tr>\n" +
                startTab + "\t\t<td align=\"left\">\n";
        if(prevParams != null)
            html += startTab + "\t\t\t" + URIUtils.getURI(path,
                    prevParams,
                    "Previous") + "\n";
        html += startTab + "\t\t</td>\n" +
                startTab + "\t\t<td align=\"right\">\n";
        if(nextParams != null)
            html += startTab + "\t\t\t" +URIUtils.getURI(path,
                    nextParams,
                    "Next") + "\n";
        html += startTab + "\t\t</td>\n" +
                startTab + "\t</tr>\n" +
                startTab + "</table>\n";
        return this;
    }

    public HtmlGenerator addBrTag()
    {
        html += startTab + "<br>\n";
        return this;
    }

    public HtmlGenerator addLink(String link)
    {
        html += startTab + link +"\n" +
            startTab + "<br>\n";
        return this;
    }

    public HtmlGenerator addString(String str)
    {
        html += startTab + str + "\n"+
                startTab + "<br>\n";
        return this;
    }

    public <T, R> HtmlGenerator htmlGenerate(Collection<T> col,
                                             Collection<R> col1,
                                             String[] head,
                                             ArrayList<Function<T, String>> func,
                                             ArrayList<Function<R, String>> func1,
                                             ArrayList<String> uri)
    {
        if (col1.size() == 1)
        {
            html += getTextTwoCollections(col, col1, head, func, func1, uri) + "\n"+
                    startTab+"<br>\n";
            return this;
        }
        html += getTableTwoCollections(col, col1, head, func, func1, uri) + "\n" + startTab+"<br>\n";
        return this;
    }

    public <T> HtmlGenerator htmlGenerate(Collection<T> col,
                                          String[] head,
                                          ArrayList<Function<T, String>> func,
                                          ArrayList<String> uri)
    {
        if (col.size() == 1)
        {
            html += getText(col, head, func, uri) + "\n" + startTab+"<br>\n";
            return this;
        }
        html += getTable(col, head, func, uri) + "\n" + startTab+"<br>\n";
        return this;
    }

    @Override
    public String toString()
    {
        return this.html;
    }

    public String getTemplate()
    {
        return this.template;
    }

    /**
     *
     * private methods
     *
     * */
    private <T> String getTable(Collection<T> col,
                                String[] head,
                                ArrayList<Function<T, String>> func,
                                ArrayList<String> uri)
    {
        String str = "\t\t\t<table border=\"1\" style=\"width:100%\">\n" + getHead(head);
        int uriIndex = 0;
        for (T t : col) {
            str += "\t\t\t\t<tr>\n";
            int aux = 0;
            for (Function<T, String> f : func) {
                if(aux == 1)
                {
                    if(uri.size() <= uriIndex)
                        str += "\t\t\t\t\t<td>" + f.apply(t) + "</td>\n";
                    else
                        str += "\t\t\t\t\t<td><a href=\""+uri.get(uriIndex)+"\">" + f.apply(t) + "</a></td>\n";
                    ++uriIndex;
                }
                else
                    str += "\t\t\t\t\t<td>" + f.apply(t) + "</td>\n";
                ++aux;
            }
            str += "\t\t\t\t</tr>\n";
        }
        str += "\t\t\t</table>";
        return str;
    }

    private <T> String getText(Collection<T> col,
                               String[] head,
                               ArrayList<Function<T, String>> func,
                               ArrayList<String> uri)
    {
        T t = col.iterator().next();
        String str = "\t\t\t<ul>\n" +
                "\t\t\t\t<li>" + head[0] + ": " + func.get(0).apply(t) + "</li>\n" +
                "\t\t\t\t<ul>\n";
        for (int i = 1; i < head.length; ++i) {
            if(i == 1)
            {
                if(uri.size() != 0)
                    str += "\t\t\t\t\t<li>" + head[i] + ": <a href=\""+uri.get(0)+"\">" + func.get(i).apply(t) + "</a></li>\n";
                else
                    str += "\t\t\t\t\t<li>" + head[i] + ": " + func.get(i).apply(t) + "</li>\n";
            }
            else
                str += "\t\t\t\t\t<li>" + head[i] + ": " + func.get(i).apply(t) + "</li>\n";
        }
        str += "\t\t\t\t</ul>\n" +
                "\t\t\t</ul>\n";
        return str;
    }

    private String getHead(String[] head)
    {
        String str = "\t\t\t\t<tr>\n";
        for (int i = 0; i < head.length; ++i)
            str += "\t\t\t\t\t<td>" + head[i] + "</td>\n";
        return str + "\t\t\t\t</tr>\n";
    }

    private <T, R> String getTableTwoCollections(Collection<T> col,
                                                 Collection<R> col1,
                                                 String[] head,
                                                 ArrayList<Function<T, String>> func,
                                                 ArrayList<Function<R, String>> func1,
                                                 ArrayList<String> uri)
    {
        T t = col.iterator().next();
        String str = "\t\t\t<table border=\"1\" style=\"width:100%\">\n"+getHead(head)+"\n" +
                "\t\t\t\t<tr>\n";
        for(Function<T, String> f : func)
        {
            str+="\t\t\t\t\t<td>"+f.apply(t)+"</td>\n";
        }
        int aux = 0, indexUri = 0;
        for(R r : col1)
        {
            if (aux > 0)
            {
                str += "\t\t\t\t<tr>\n";
                for(int i = 0; i < head.length - func1.size(); ++i)
                {
                    str += "\t\t\t\t\t<td></td>\n";
                }
            }
            int uriAux = 0;
            for(Function<R, String> f : func1)
            {
                if(uriAux == 1)
                {
                    if(uri.size() <= indexUri)
                        str += "\t\t\t\t\t<td>"+f.apply(r)+"</td>\n";
                    else
                        str += "\t\t\t\t\t<td><a href=\""+uri.get(indexUri)+"\">"+f.apply(r)+"</a></td>\n";
                    ++indexUri;
                }
                else
                {
                    str += "\t\t\t\t\t<td>"+f.apply(r)+"</td>\n";
                }
                ++uriAux;
            }
            ++aux;
            str += "\t\t\t\t</tr>\n";
        }
        return str+"\t\t\t</table>";
    }

    private static <T, R> String getTextTwoCollections(Collection<T> col,
                                                       Collection<R> col1,
                                                       String[] head,
                                                       ArrayList<Function<T, String>> func,
                                                       ArrayList<Function<R, String>> func1,
                                                       ArrayList<String> uri)
    {
        T t = col.iterator().next();
        String str = "\t\t\t<ul>\n";
        int aux = 0;
        for(Function<T, String> f : func)
        {
            if(aux == 0)
            {
                str += "\t\t\t\t<li>"+head[aux]+": "+f.apply(t)+"</li>\n" +
                        "\t\t\t\t\t<ul>\n";
            }
            else

            {
                str += "\t\t\t\t\t\t<li>"+head[aux]+": "+f.apply(t)+"</li>\n";
            }
            ++aux;
        }
        str += "\t\t\t\t\t\t\t<ul>\n";
        R r = col1.iterator().next();
        int aux1 = 0;
        for (Function<R, String> f : func1)
        {
            str += "\t\t\t\t\t\t\t\t<li>";
            if(aux1 == 1)
            {
                if(uri.size() != 0)
                    str += head[aux]+": <a href=\""+uri.get(0)+"\">"+ f.apply(r)+"</a>";
                else
                    str += head[aux]+": "+ f.apply(r)+"";
            }

            else
                str += head[aux]+": "+ f.apply(r)+"";
            ++aux1;
            ++aux;
            str += "</li>\n";
        }
        str += "\t\t\t\t\t\t\t</ul>\n" +
                "\t\t\t\t\t</ul>\n" +
                "\t\t\t</ul>";
        return str;
    }

}
