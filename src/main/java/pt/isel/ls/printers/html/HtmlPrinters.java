package pt.isel.ls.printers.html;


import java.util.ArrayList;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Dani on 12-04-2016.
 */
public class HtmlPrinters {
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

    public static <T, R> String htmlGenerate(Collection<T> col, Collection<R> col1, String[] head,
                                             ArrayList<Function<T, String>> func, ArrayList<Function<R, String>> func1,
                                             ArrayList<String> uri)
    {
        if (col1.size() == 1)
            return getTextTwoCollections(col, col1, head, func, func1, uri);
        return getTableTwoCollections(col, col1, head, func, func1, uri);
    }

    private static <T, R> String getTableTwoCollections(Collection<T> col, Collection<R> col1, String[] head,
                                                        ArrayList<Function<T, String>> func,
                                                        ArrayList<Function<R, String>> func1, ArrayList<String> uri)
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

    private static <T, R> String getTextTwoCollections(Collection<T> col, Collection<R> col1, String[] head,
                                                       ArrayList<Function<T, String>> func,
                                                       ArrayList<Function<R, String>> func1, ArrayList<String> uri)
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

    public static <T> String htmlGenerate(Collection<T> col, String[] head, ArrayList<Function<T, String>> func, ArrayList<String> uri) {
        if (col.size() == 1)
            return getText(col, head, func, uri);
        return getTable(col, head, func, uri);
    }

    private static <T> String getTable(Collection<T> col, String[] head, ArrayList<Function<T, String>> func, ArrayList<String> uri) {
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

    private static <T> String getText(Collection<T> col, String[] head, ArrayList<Function<T, String>> func, ArrayList<String> uri) {
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

    private static String getHead(String[] head) {
        String str = "\t\t\t\t<tr>\n";
        for (int i = 0; i < head.length; ++i)
            str += "\t\t\t\t\t<td>" + head[i] + "</td>\n";
        return str + "\t\t\t\t</tr>\n";
    }

}
