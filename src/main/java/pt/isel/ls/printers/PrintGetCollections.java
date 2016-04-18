package pt.isel.ls.printers;


import java.util.Collection;


/**
 * "\nCollection id = #\nName = #\nDescription = #\nDate of creation = #"; (NOTA: nao esquecer \n final)
 */
public class PrintGetCollections implements Printable {

    Collection<pt.isel.ls.model.Collections> col;

    public PrintGetCollections(Collection<pt.isel.ls.model.Collections> c){
        col = c;
    }

    @Override
    public String toStringResult() {
        String s = "";
        for (pt.isel.ls.model.Collections collection:col) {
            s +=    "\nCollection id = " + collection.getCollectionID() +
                    "\nName = " + collection.getName() +
                    "\nDescription = " + collection.getDescription() +
                    "\nDate of creation = " +collection.getCreationDate();
        }
        return s + "\n";
    }
}
