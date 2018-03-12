package xyz.ivyxjc.pojogenerator;

import freemarker.template.TemplateException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import xyz.ivyxjc.pojogenerator.config.DatabaseConfig;
import xyz.ivyxjc.pojogenerator.core.DB;
import xyz.ivyxjc.pojogenerator.core.GenerateCoreTaskDistribution;
import xyz.ivyxjc.pojogenerator.model.Schema;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, TemplateException {
        AbstractApplicationContext context =
                new AnnotationConfigApplicationContext(DatabaseConfig.class);
        DB db = context.getBean(DB.class);
        GenerateCoreTaskDistribution taskDistribution = context.getBean(GenerateCoreTaskDistribution.class);
        Schema schema =
                db.getDbStructure(
                        null, PropertiesProvider.INSTANCE.getSchema(), PropertiesProvider.INSTANCE.getTableNamePattern());


        for (String table : schema.getTablesMap().keySet()) {
            taskDistribution.taskDistribution(schema.getTable(table));
        }

    }
}
