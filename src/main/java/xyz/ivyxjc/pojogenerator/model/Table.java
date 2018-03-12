package xyz.ivyxjc.pojogenerator.model;

import xyz.ivyxjc.pojogenerator.utils.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private String tableName;
    private String tableCamelName;
    private String tableCamelNameFirstUpper;
    private Map<String, Column> mColumnMap;


    public Table() {
        mColumnMap = new HashMap<>();
    }

    public Table(String tableName) {
        this.mColumnMap = new HashMap<>();
        this.tableName = tableName;
        this.tableCamelName = CommonUtils.convertDbTypeToCamel(tableName);
        this.tableCamelNameFirstUpper =
                Character.toUpperCase(tableCamelName.charAt(0))
                        + tableCamelName.substring(1, tableCamelName.length());
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.tableCamelName = CommonUtils.convertDbTypeToCamel(tableName);
        this.tableCamelNameFirstUpper =
                Character.toUpperCase(tableCamelName.charAt(0))
                        + tableCamelName.substring(1, tableCamelName.length());
    }

    public void setSpecificClassName(String specificClassName) {
        tableCamelNameFirstUpper = specificClassName;
    }

    public Column getColumn(String columnName) {
        return mColumnMap.get(columnName);
    }

    public Column removeColumn(String columnName) {
        return mColumnMap.remove(columnName);
    }

    public void addColumn(Column column) {
        mColumnMap.put(column.getColumnName(), column);
    }

    public void putAllColumns(Table table) {
        mColumnMap.putAll(table.getColumnMap());
    }

    public List<Column> getColumns() {
        return new ArrayList<>(mColumnMap.values());
    }

    public Map<String, Column> getColumnMap() {
        return mColumnMap;
    }

    public void setColumnMap(Map<String, Column> columnMap) {
        mColumnMap = columnMap;
    }

    public String getTableCamelName() {
        return tableCamelName;
    }

    public void setTableCamelName(String tableCamelName) {
        this.tableCamelName = tableCamelName;
    }

    public String getTableCamelNameFirstUpper() {
        return tableCamelNameFirstUpper;
    }

    public void setTableCamelNameFirstUpper(String tableCamelNameFirstUpper) {
        this.tableCamelNameFirstUpper = tableCamelNameFirstUpper;
    }
}


