package pt.isel.ls.database.printers;

/**
 * Public interface of Printable Objects
 * Classes that implements this class have the responsability of print the results of all commands
 */
public interface Printable {

    public String toStringResult();
}
