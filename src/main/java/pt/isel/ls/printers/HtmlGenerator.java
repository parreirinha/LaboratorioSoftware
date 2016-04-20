package pt.isel.ls.printers;


import com.sun.xml.internal.messaging.saaj.util.SAAJUtil;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Dani on 12-04-2016.
 */
public class HtmlGenerator<T>
{
    private final static String s = "<!DOCTYPE>\n" +
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
            "\t\t\t%s" +
            "\n" +
            "\t\t</body>\n" +
            "\t</html>";

    public static <T> String htmlGenerate(Collection<T> col, String[] head, Function<T, String>[] func)
    {
        if(col.size() == 1)
            return String.format(s, getText(col, head, func));
        return String.format(s, getTable(col, head, func));
    }

    private static <T> String getTable(Collection<T> col, String[] head, Function<T, String>[] func)
    {
        String str = "<table border=\"1\" style=\"width:100%\">\n" + getHead(head);
        for(T t : col)
        {
            str += "<tr>\n";
            for(Function<T,String> f : func)
            {
                str += "\t\t<td>"+f.apply(t)+"</td>\n";
            }
            str += "</tr>\n";
        }
        str += "</table>";
        return str;
    }

    private static <T> String getText(Collection<T> col, String[] head, Function<T, String>[] func)
    {
        String str = "<ul>\n" +
                "<li>"+head[0]+": "+func[0]+"</li>\n" +
                "<li>\n" +
                "<ul>\n";
        for(int i = 1; i < head.length; ++i)
        {
            str += "<li>"+head[i]+": "+func[i]+"</li>\n";
        }
        str += "</ul>\n" +
                "</li>\n";
        return str;
    }

    private static String getHead(String[] head)
    {
        String str = "<tr>\n";
        for(int i = 0; i < head.length; ++i)
            str += "\t\t<td>"+head[i]+"</td>\n";
        return str + "</tr>\n";
    }

}
