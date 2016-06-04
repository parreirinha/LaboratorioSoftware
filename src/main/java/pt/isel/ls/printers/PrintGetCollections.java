package pt.isel.ls.printers;

import pt.isel.ls.linecommand.model.Command;
import pt.isel.ls.model.Collections;
import pt.isel.ls.printers.URIGenerator.URIUtils;
import pt.isel.ls.printers.html.HtmlGenerator;

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
    public String toStringHtml()
    {
        HtmlGenerator htmlString = new HtmlGenerator();
        ArrayList<String> uri = new ArrayList<>();
        col.forEach(x-> uri.add("/collections/"+x.getCollectionID()));
        ArrayList<String> menu = new ArrayList<>();
        menu.add(URIUtils.getURI("/", null, "Home Page"));
        htmlString
                .createMenu(menu)
                .htmlGenerate(col, head, functions, uri)
                .createPagging(command, "/collections/")
                .postNewCollectionForm();

        return String.format(htmlString.getTemplate(), htmlString.toString());
    }

}
