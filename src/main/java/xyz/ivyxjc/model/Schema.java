package xyz.ivyxjc.model;

import java.util.HashMap;
import java.util.Map;

public class Schema {
    private Map<String, Table> tablesMap;

    public Schema() {
        tablesMap = new HashMap<>();
    }

    public void addtabl(Table table) {
        tablesMap.put(table.getTableName(), table);
    }

    public boolean containsTable(String tableName) {
        return tablesMap.containsKey(tableName);
    }

    public Table getTable(String tableName) {
        return tablesMap.get(tableName);
    }

    public Map<String, Table> getTablesMap() {
        return tablesMap;
    }

    public void setTablesMap(Map<String, Table> tablesMap) {
        this.tablesMap = tablesMap;
    }
}
