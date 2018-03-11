package xyz.ivyxjc.model;

import xyz.ivyxjc.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String tableName;
    private String tableCamelName;
    private String tableCamelNameFirstUpper;
    private List<Column> mColumns;

    public Table() {
        mColumns = new ArrayList<>();
    }

    public Table(String tableName) {
        this.mColumns = new ArrayList<>();
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

    public void addColumn(Column column) {
        mColumns.add(column);
    }

    public List<Column> getColumns() {
        return mColumns;
    }

    public void setColumns(List<Column> columns) {
        mColumns = columns;
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
