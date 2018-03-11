package xyz.ivyxjc;

import java.util.HashMap;
import java.util.Map;

public class TypesMap {

    private static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(-15, "Types.NCHAR");
        map.put(-9, "Types.NVARCHAR");
        map.put(-16, "Types.LONGNVARCHAR");
        map.put(2011, "Types.NCLOB");
        map.put(2009, "Types.SQLXML");
        map.put(2012, "Types.REF_CURSOR");
        map.put(2013, "Types.TIME_WITH_TIMEZONE");
        map.put(2014, "Types.TIMESTAMP_WITH_TIMEZONE");
        map.put(-7, "Types.BIT");
        map.put(-6, "Types.TINYINT");
        map.put(5, "Types.SMALLINT");
        map.put(4, "Types.INTEGER");
        map.put(-5, "Types.BIGINT");
        map.put(6, "Types.FLOAT");
        map.put(7, "Types.REAL");
        map.put(8, "Types.DOUBLE");
        map.put(2, "Types.NUMERIC");
        map.put(3, "Types.DECIMAL");
        map.put(1, "Types.CHAR");
        map.put(12, "Types.VARCHAR");
        map.put(-1, "Types.LONGVARCHAR");
        map.put(91, "Types.DATE");
        map.put(92, "Types.TIME");
        map.put(93, "Types.TIMESTAMP");
        map.put(-2, "Types.BINARY");
        map.put(-3, "Types.VARBINARY");
        map.put(-4, "Types.LONGVARBINARY");
        map.put(0, "Types.NULL");
        map.put(1111, "Types.OTHER");
        map.put(2000, "Types.JAVA_OBJECT");
        map.put(2001, "Types.DISTINCT");
        map.put(2002, "Types.STRUCT");
        map.put(2003, "Types.ARRAY");
        map.put(2004, "Types.BLOB");
        map.put(2005, "Types.CLOB");
        map.put(2006, "Types.REF");
        map.put(70, "Types.DATALINK");
        map.put(16, "Types.BOOLEAN");
        map.put(-8, "Types.ROWID");
    }

    public static String get(int key) {
        return map.get(key);
    }
}
