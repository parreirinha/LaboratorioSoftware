package pt.isel.ls.linecommand.process;

import pt.isel.ls.linecommand.mapping.CommandMapper;
import pt.isel.ls.linecommand.model.Path;

import java.util.HashMap;


/**
 * Class used to process the line command path string and generate an instance of Path.
 */
public class PathGetter {
    private HashMap<String, Integer> pathIntegers;
    private String[][] PathTemplateContainer;
    private String path;
    private String[] pathParts;

    public PathGetter() {
        PathTemplateContainer = getPathTemplatesFromCommandMapper();
    }


    public Path getPath(String path) {
        this.path = path;
        boolean match = false;
        if (!path.equals("") && !path.equals("/")) {
            pathParts = path.split("/");
            pathParts = cleanFirstPositionOfStringArray(pathParts);

            for (int i = 0; i < PathTemplateContainer.length; ++i) {
                String[] template = PathTemplateContainer[i];
                if (pathTemplateComparator(pathParts, template)) {
                    pathIntegers = putIdsInPathMap(pathParts, template);
                    this.path = concatPathTemplate(template);
                    match = true;
                    break;
                }
            }
            if (!match)
                this.path = "wrong";
        }

        return new Path(this.path, pathIntegers);
    }

    private String concatPathTemplate(String[] template) {
        String templateString = "";

        for (int i = 0; i < template.length; i++) {
            templateString += "/" + template[i];
        }

        return templateString;
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

    private String[][] getPathTemplatesFromCommandMapper() {
        String[][] paths = new CommandMapper()
                .getCommandMapKeys()
                .stream()
                .distinct()
                .map(s -> {
                    return getPathTemplateParts(s);
                })
                .toArray(String[][]::new);

        return paths;
    }

    private String[] getPathTemplateParts(String pathString) {
        if (!pathString.equals("") && !pathString.equals("/")) {
            return cleanFirstPositionOfStringArray(pathString.split("/"));
        }
        String[] out = {pathString};
        return out;
    }

    private String[] cleanFirstPositionOfStringArray(String[] pathParts) {
        String[] aux = new String[pathParts.length - 1];
        for (int i = 0; i < aux.length; ++i) {
            aux[i] = pathParts[i + 1];
        }
        return aux;
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
        if (s.length() == 0) return false;
        return s.charAt(0) == '{' && s.charAt(s.length() - 1) == '}';
    }

    private String cleanPathPart(String string) {
        String s = "";
        for (int i = 1; i < string.length() - 1; ++i) {
            s += string.charAt(i);
        }
        return s;
    }
}
