package xyz.ivyxjc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.ivyxjc.model.Column;
import xyz.ivyxjc.model.Schema;
import xyz.ivyxjc.model.Table;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DB {

    @Autowired
    private JdbcTemplate mJdbcTemplate;

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
