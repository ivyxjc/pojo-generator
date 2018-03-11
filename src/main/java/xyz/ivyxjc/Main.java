package xyz.ivyxjc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import xyz.ivyxjc.config.DatabaseConfig;
import xyz.ivyxjc.model.Table;

public class Main {


    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        DB db = context.getBean(DB.class);
        GeneratorCore generatorCore = context.getBean(GeneratorCore.class);
        Table table = db.getDbStructure(null, "douban", "movie_detail");
        generatorCore.generateCore(table, "xyz.ivyxjc");
    }

}
