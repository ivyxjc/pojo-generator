package xyz.ivyxjc;

public @interface DBColumn {
    Class javaType();

    int columnType();

    String columnName();
}
