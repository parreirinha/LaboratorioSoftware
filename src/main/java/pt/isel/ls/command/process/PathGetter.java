package pt.isel.ls.command.process;

import pt.isel.ls.command.model.Path;

import java.util.ArrayList;

/**
 * Class used to process a path string and generate the
 * correspondent Path instance.
 */
public class PathGetter {

    public Path getPath(String path){

        String[] pathParts = path.split("/");
        String cleanPath = "";
        ArrayList<Integer> ids = new ArrayList<Integer>();

        int j=0;
        for(int i = 0; i < pathParts.length; ++i) {
            if (!isAllDigits(pathParts[i]))
                cleanPath += pathParts[i];
            else{
                if(i>0) {
                    if (pathParts[i - 1].equals("movies"))
                        cleanPath += "mid";
                    if (pathParts[i - 1].equals("reviews"))
                        cleanPath += "rid";
                    if (pathParts[i - 1].equals("tops"))
                        cleanPath += "n";

                    ids.add(Integer.parseInt(pathParts[i]));
                }
            }
        }

        return new Path(cleanPath, ids);
    }

    static boolean isAllDigits(String pathPart){
        boolean itIs = true;

        for(int i = 0; i < pathPart.length(); ++i){
            if(pathPart.charAt(i) < '0' || pathPart.charAt(i) > '9')
                itIs = false;
        }
        return itIs;
    }
}
