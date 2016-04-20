package pt.isel.ls.printers;

/**
 * Public interface of Printable Objects
 * Classes that implements this class have the responsability of print the results of all access
 */
public interface Printable
{
    /*
    default public String getHead()
    {
        return "<!DOCTYPE>\n" +
                "\t<html>\n" +
                "\t\t<head>\n" +
                "\t\t\t<meta\n" +
                "\t\t\t\thttp-equiv=\"Content-Type\"\n" +
                "\t\t\t\tcontent=\"text/html; charset=utf-8\"\n" +
                "\t\t\t/>" +
                "\n" +
                "\t\t</head>\n" +
                "\n" +
                "\t\t<body>\n\n" +
                "\t\t\t%s" +
                "\n" +
                "\t\t</body>\n" +
                "\t</html>";
    }
*/
    public String toStringText();
    public String toStringHtml();

}