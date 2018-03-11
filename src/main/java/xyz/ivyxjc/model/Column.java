package xyz.ivyxjc.model;

import com.mysql.cj.core.MysqlType;
import xyz.ivyxjc.TypesMap;
import xyz.ivyxjc.utils.CommonUtils;

public class Column {
    private Class javaType;
    private int columnDbType;
    private String columnDbTypeStr;
    private String columnName;
    private String columnCamelName;
    private String columnCamelNameWithFirstUpper;
    private String tableName;
    private String schemaName;

    public Column(String columnName, int columnDbType) {
        this.columnName = columnName;
        this.columnDbType = columnDbType;
        this.columnDbTypeStr = TypesMap.get(columnDbType);
        String javaTypeStr = MysqlType.getByJdbcType(columnDbType).getClassName();
        try {
            if (javaTypeStr.equals("[B")) {
                javaType = String.class;
            } else {
                javaType = Class.forName(javaTypeStr);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            javaType = String.class;
        }
        this.columnCamelName = CommonUtils.convertDbTypeToCamel(columnName);
        this.columnCamelNameWithFirstUpper =
                Character.toUpperCase(columnCamelName.charAt(0))
                        + columnCamelName.substring(1, columnCamelName.length());
    }

    public Class getJavaType() {
        return javaType;
    }

    public void setJavaType(Class javaType) {
        this.javaType = javaType;
    }

    public int getColumnDbType() {
        return columnDbType;
    }

    public void setColumnDbType(int columnDbType) {
        this.columnDbType = columnDbType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnCamelName() {
        return columnCamelName;
    }

    public void setColumnCamelName(String columnCamelName) {
        this.columnCamelName = columnCamelName;
    }

    public String getColumnCamelNameWithFirstUpper() {
        return columnCamelNameWithFirstUpper;
    }

    public void setColumnCamelNameWithFirstUpper(String columnCamelNameWithFirstUpper) {
        this.columnCamelNameWithFirstUpper = columnCamelNameWithFirstUpper;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getColumnDbTypeStr() {
        return columnDbTypeStr;
    }

    public void setColumnDbTypeStr(String columnDbTypeStr) {
        this.columnDbTypeStr = columnDbTypeStr;
    }
}
