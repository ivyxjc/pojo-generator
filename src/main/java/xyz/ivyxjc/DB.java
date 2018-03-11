package xyz.ivyxjc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.ivyxjc.model.Column;
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

    public Table getDbStructure(
            String catalog, String schema, String tableName, String columnNamePattern) {
        Table table = new Table();
        try {
            ResultSet rs =
                    mDataSource
                            .getConnection()
                            .getMetaData()
                            .getColumns(catalog, schema, tableName, columnNamePattern);

            table.setTableName(tableName);
            while (rs.next()) {
                Column column = new Column(rs.getString("COLUMN_NAME"), rs.getInt("DATA_TYPE"));
                column.setTableName(rs.getString("TABLE_NAME"));
                table.addColumn(column);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

    public Table getDbStructure(String catalog, String schema, String tableName) {
        return getDbStructure(catalog, schema, tableName, "%");
    }
}
