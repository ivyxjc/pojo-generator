package xyz.ivyxjc.utils;

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
}
