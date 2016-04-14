package pt.isel.ls.linecommand.process;

import pt.isel.ls.linecommand.model.Path;

import java.util.HashMap;

/**
 * Class used to process a path string and generate the
 * correspondent Path instance.
 */
public class PathGetter {
    private String[] pathParts;
    private String cleanPath;
    private HashMap<String, Integer> pathIntegers;

    public PathGetter(){
        cleanPath = "";
        pathIntegers = new HashMap<String, Integer>();
    }

    public Path getPath(String path){

        pathParts = path.split("/");

        if(path!="")
        for(int i = 0; i < pathParts.length; ++i) {
            if (!isAllDigits(pathParts[i]))
                cleanPath += pathParts[i];
            else{
                if(i>0) {
                    String key="";
                    if (pathParts[i - 1].equals("movies"))
                        key = "mid";

                    if (pathParts[i - 1].equals("reviews"))
                        key = "rid";

                    if (pathParts[i - 1].equals("tops"))
                        key = "n";

                    if (pathParts[i - 1].equals("collections"))
                        key = "cid";

                    cleanPath += key;
                    pathIntegers.put(key, Integer.parseInt(pathParts[i]));
                }
            }
        }

        return new Path(cleanPath, pathIntegers);
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
