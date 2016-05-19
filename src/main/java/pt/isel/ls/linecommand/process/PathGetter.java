package pt.isel.ls.linecommand.process;

import pt.isel.ls.linecommand.model.Path;

import java.util.HashMap;

/**
 * Class responsible to create a new Path instance from the
 * path string given in the command line.
 */
public class PathGetter {
    private String[] pathParts;
    private String cleanPath = "";
    private HashMap<String, Integer> pathIntegers;

    private final String[][] PathTemplateContainer = {
            {"movies", "{mid}"},
            {"movies"},
            {"movies", "{mid}", "ratings"},
            {"movies", "{mid}", "reviews"},
            {"movies", "{mid}", "reviews", "{rid}"},
            {"tops", "ratings", "higher", "average"},
            {"tops", "{n}", "ratings", "higher", "average"},
            {"tops", "ratings", "lower", "average"},
            {"tops", "{n}", "ratings", "lower", "average"},
            {"tops", "reviews", "higher", "count"},
            {"tops", "{n}", "reviews", "higher", "count"},
            {"collections"},
            {"collections", "{cid}"},
            {"collections", "{cid}", "movies"},
            {"collections", "{cid}", "movies", "{mid}"},
            {"tops","ratings"}
    };

    public Path getPath(String path) {
        if (!path.equals("") && !path.equals("/")) {
            pathParts = path.split("/");
            pathParts = cleanFirstPositionOfStringArray(pathParts);

            for (int i = 0; i < PathTemplateContainer.length; ++i) {
                if (pathTemplateComparator(pathParts, PathTemplateContainer[i])) {
                    cleanPath = getStringFromPathTemplate(PathTemplateContainer[i]);
                    pathIntegers = putIdsInPathMap(pathParts, PathTemplateContainer[i]);
                    break;
                }
            }
        }
        return new Path(cleanPath, pathIntegers);
    }

    private HashMap<String, Integer> putIdsInPathMap(String[] pathParts, String[] template) {
        HashMap<String, Integer> map = new HashMap<>();
        int size = pathParts.length;
        for (int i = 0; i < size; ++i) {
            if (isPathVariable(template[i])) {
                map.put(cleanPathPart(template[i]), Integer.parseInt(pathParts[i]));
            }
        }
        return map;
    }

    private String[] cleanFirstPositionOfStringArray(String[] pathParts) {
        String[] aux = new String[pathParts.length - 1];
        for (int i = 0; i < aux.length; ++i) {
            aux[i] = pathParts[i + 1];
        }
        return aux;
    }

    private String getStringFromPathTemplate(String[] strings) {
        String s = "";
        for (int i = 0; i < strings.length; ++i) {
            if (isPathVariable(strings[i])) {
                s += cleanPathPart(strings[i]);
            } else {
                s += strings[i];
            }
        }
        return s;
    }

    private String cleanPathPart(String string) {
        String s = "";
        for (int i = 1; i < string.length() - 1; ++i) {
            s += string.charAt(i);
        }
        return s;
    }

    private boolean pathTemplateComparator(String[] path, String[] template) {
        int size = path.length;
        if (size != template.length) {
            return false;
        }
        boolean isSamePath = true;
        for (int i = 0; i < size; ++i) {
            if (!path[i].equals(template[i]) && !isPathVariable(template[i])) {
                isSamePath = false;
                break;
            }
        }

        return isSamePath;
    }

    private boolean isPathVariable(String s) {
        return s.charAt(0) == '{' && s.charAt(s.length() - 1) == '}';
    }

}
