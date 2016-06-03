package pt.isel.ls.printers;

import pt.isel.ls.printers.html.HtmlGenerator;

/**
 * Class used to print a exit message.
 */
public class PrintExit implements Printable {
    private final String Exit = "Exiting application. Thank you and good bye.";

    @Override
    public String toStringText() {
        return Exit;
    }

    @Override
    public String toStringHtml()
    {
        return String.format(new HtmlGenerator().getTemplate(), Exit);
    }
}
