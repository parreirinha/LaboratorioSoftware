package pt.isel.ls.printers;


import pt.isel.ls.http.ExecutionServlet;
import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Collections;
import pt.isel.ls.printers.html.HtmlPrinters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;


/**
 * "\nCollection id = #\nName = #\nDescription = #"; (NOTA: nao esquecer \n final)
 */
public class PrintGetCollections implements Printable {

    private final Command command;
    Collection<Collections> col;
    ArrayList<Function<Collections, String>> functions = new ArrayList<>();
    String[] head = {"Collection id", "Name", "Description"};

    public PrintGetCollections(Collection<Collections> c, Command command) {
        this.command = command;
        col = c;
        functions.add(col -> "" + col.getCollectionID());
        functions.add(col -> col.getName());
        functions.add(col -> col.getDescription());
    }

    @Override
    public String toStringText() {
        String s = "";
        for (Collections collection : col) {
            s += "\n" + head[0] + " = " + collection.getCollectionID() +
                    "\n" + head[1] + " = " + collection.getName() +
                    "\n" + head[2] + " = " + collection.getDescription();
        }
        return s + "\n";
    }

    @Override
    public String toStringHtml() {
        /*
        if(col.size() == 1)
            return String.format(Printable.super.getTemplate(), getText());
        return String.format(Printable.super.getTemplate(), getTable());
        */
        ArrayList<String> uri = new ArrayList<>();
        col.forEach(x-> uri.add("http://localhost:"+ ExecutionServlet.getPort()+"/collections/"+x.getCollectionID()));
        return HtmlPrinters.htmlGenerate(col, head, functions, uri);
    }


    private String getTable() {
        String str = "<table border=\"1\" style=\"width:100%\">\n" +
                "\t" + getFullHtmlTitle() + "\n";
        for (Collections c : col) {
            str += "\t" + getFullHtmlDescription(c) + "\n";
        }
        str += "</table>";
        return str;
    }

    private String getText() {
        Collections c = col.iterator().next();
        String str = "<ul><li>" + head[0] + ": " + functions.get(0).apply(c) + "</li>\n" +
                "<ul>\n";
        for (int i = 1; i < head.length; ++i) {
            str += "<li>" + head[i] + ": " + functions.get(i).apply(c) + "</li>\n";
        }
        str += "</ul>\n" +
                "</ul>\n";
        return str;
    }

    private String getFullHtmlDescription(Collections c) {
        String str = "<tr>\n";
        for (int i = 0; i < functions.size(); ++i)
            str += "\t\t<td>" + functions.get(i).apply(c) + "</td>\n";
        return str + "</tr>\n";
    }

    private String getFullHtmlTitle() {
        String str = "<tr>\n";
        for (int i = 0; i < head.length; ++i)
            str += "\t\t<td>" + head[i] + "</td>\n";
        return str + "</tr>\n";
    }


}
