package xyz.ivyxjc.pojogenerator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import xyz.ivyxjc.pojogenerator.config.DatabaseConfig;
import xyz.ivyxjc.pojogenerator.core.BaseMethodGenerate;
import xyz.ivyxjc.pojogenerator.core.DB;
import xyz.ivyxjc.pojogenerator.core.GeneratorCore;
import xyz.ivyxjc.pojogenerator.model.Schema;
import xyz.ivyxjc.pojogenerator.model.Table;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        AbstractApplicationContext context =
                new AnnotationConfigApplicationContext(DatabaseConfig.class);
        DB db = context.getBean(DB.class);
        GeneratorCore generatorCore = context.getBean(GeneratorCore.class);
        BaseMethodGenerate baseMethodGenerate = context.getBean(BaseMethodGenerate.class);
        Schema schema =
                db.getDbStructure(
                        null, PropertiesProvider.INSTANCE.getSchema(), PropertiesProvider.INSTANCE.getTable());
        for (String tableName : schema.getTablesMap().keySet()) {
            System.out.println(tableName);
            Table table = schema.getTable(tableName);
            baseMethodGenerate.handle(table);
            generatorCore.generateCore(table, PropertiesProvider.INSTANCE.getPackageName(), false);
        }

    }
}
