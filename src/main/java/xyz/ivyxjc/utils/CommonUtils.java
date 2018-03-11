package xyz.ivyxjc.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommonUtils {

    private CommonUtils() {
    }

    public static String convertDbTypeToCamel(String columnName) {
        List<Character> res = new ArrayList<>();
        int length = columnName.length();
        for (int i = 0; i < length; ) {
            if (columnName.charAt(i) == '_') {
                if (i + 1 < length) {
                    res.add(Character.toUpperCase(columnName.charAt(i + 1)));
                }
                i += 2;
                continue;
            }
            res.add(columnName.charAt(i));
            i += 1;
        }
        return res.stream().map(e -> e.toString()).collect(Collectors.joining());
    }

    /**
     * make sure that this path exists
     *
     * @param path
     */
    public static void enablePathExist(String path) {
        String[] pathDetails = path.split("\\\\");
        String base = "";
        for (int i = 0; i < pathDetails.length; i++) {
            if (i == 0) {
                base += pathDetails[i];
            } else {
                base += "\\" + pathDetails[i];
            }
            File baseFile = new File(base);
            if (baseFile.exists() && baseFile.isDirectory()) {
            } else {
                baseFile.mkdir();
            }
        }
    }
}
