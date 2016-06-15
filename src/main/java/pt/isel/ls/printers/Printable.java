package pt.isel.ls.printers;

/**
 * Public interface of Printable Objects
 * Classes that implement this class have the responsability of print the results of all access
 */
public interface Printable {

    public String toStringText();

    public String toStringHtml();

}