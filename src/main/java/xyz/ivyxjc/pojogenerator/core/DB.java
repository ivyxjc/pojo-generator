package xyz.ivyxjc.pojogenerator.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.ivyxjc.pojogenerator.model.Column;
import xyz.ivyxjc.pojogenerator.model.Schema;
import xyz.ivyxjc.pojogenerator.model.Table;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DB {

    @Autowired
    private DataSource mDataSource;

    public Schema getDbStructure(
            String catalog, String schemaStr, String tableNamePattern, String columnNamePattern) {
        Schema schema = new Schema();
        Table table;
        try {
            ResultSet rs =
                    mDataSource
                            .getConnection()
                            .getMetaData()
                            .getColumns(catalog, schemaStr, tableNamePattern, columnNamePattern);

            while (rs.next()) {
                Column column = new Column(rs.getString("COLUMN_NAME"), rs.getInt("DATA_TYPE"));
                column.setTableName(rs.getString("TABLE_NAME"));
                if (schema.containsTable(column.getTableName())) {
                    table = schema.getTable(column.getTableName());
                } else {
                    table = new Table();
                    table.setTableName(column.getTableName());
                    schema.addtabl(table);
                }
                table.addColumn(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schema;
    }

    public Schema getDbStructure(String catalog, String schema, String tableName) {
        return getDbStructure(catalog, schema, tableName, "%");
    }
}
