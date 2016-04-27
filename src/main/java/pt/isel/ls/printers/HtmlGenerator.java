package pt.isel.ls.printers;




import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Dani on 12-04-2016.
 */
public class HtmlGenerator<T>
{
    public final static String template = "<!DOCTYPE>\n" +
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
            "\t</html>";

    public static <T> String htmlGenerate(Collection<T> col, String[] head, Function<T, String>[] func)
    {
        if(col.size() == 1)
            return String.format(template, getText(col, head, func));
        return String.format(template, getTable(col, head, func));
    }

    private static <T> String getTable(Collection<T> col, String[] head, Function<T, String>[] func)
    {
        String str = "\t\t\t<table border=\"1\" style=\"width:100%\">\n" + getHead(head);
        for(T t : col)
        {
            str += "\t\t\t\t<tr>\n";
            for(Function<T,String> f : func)
            {
                str += "\t\t\t\t\t<td>"+f.apply(t)+"</td>\n";
            }
            str += "\t\t\t\t</tr>\n";
        }
        str += "\t\t\t</table>";
        return str;
    }

    private static <T> String getText(Collection<T> col, String[] head, Function<T, String>[] func)
    {
        T t = col.iterator().next();
        String str = "\t\t\t<ul>\n" +
                "\t\t\t\t<li>"+head[0]+": "+func[0].apply(t)+"</li>\n" +
                "\t\t\t\t<ul>\n";
        for(int i = 1; i < head.length; ++i)
        {
            str += "\t\t\t\t\t<li>"+head[i]+": "+func[i].apply(t)+"</li>\n";
        }
        str += "\t\t\t\t</ul>\n" +
                "\t\t\t</ul>\n";
        return str;
    }

    private static String getHead(String[] head)
    {
        String str = "\t\t\t\t<tr>\n";
        for(int i = 0; i < head.length; ++i)
            str += "\t\t\t\t\t<td>"+head[i]+"</td>\n";
        return str + "\t\t\t\t</tr>\n";
    }

}
