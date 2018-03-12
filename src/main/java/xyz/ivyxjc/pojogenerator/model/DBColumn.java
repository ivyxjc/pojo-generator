package xyz.ivyxjc.pojogenerator.model;

public @interface DBColumn {
    Class javaType();

    int columnType();

    String columnName();
}
