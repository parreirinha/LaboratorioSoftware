package pt.isel.ls.printers;


import pt.isel.ls.model.Collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;


/**
 * "\nCollection id = #\nName = #\nDescription = #"; (NOTA: nao esquecer \n final)
 */
public class PrintGetCollections implements Printable {

    Collection<Collections> col;
    ArrayList<Function<Collections, String>> functions = new ArrayList<>();
    String[] head = {"Collection id", "Name", "Description"};

    public PrintGetCollections(Collection<Collections> c){
        col = c;
        functions.add(col -> "" + col.getCollectionID());
        functions.add(col -> col.getName());
        functions.add(col -> col.getDescription());
    }

    @Override
    public String toStringText() {
        String s = "";
        for (Collections collection : col) {
            s +=    "\n"+head[0]+" = " + collection.getCollectionID() +
                    "\n"+head[1]+" = " + collection.getName() +
                    "\n"+head[2]+" = " + collection.getDescription();
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
        return HtmlGenerator.htmlGenerate(col, head, functions);
    }


}
