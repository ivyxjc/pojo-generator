package xyz.ivyxjc.pojogenerator.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import xyz.ivyxjc.pojogenerator.Main;
import xyz.ivyxjc.pojogenerator.PropertiesProvider;
import xyz.ivyxjc.pojogenerator.model.Column;
import xyz.ivyxjc.pojogenerator.model.Table;
import xyz.ivyxjc.pojogenerator.utils.CommonUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@Service
public class GeneratorCore {

    public void generateCore(Table table, String packageName, String className, boolean isBase) {
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        cfg.setClassForTemplateLoading(Main.class, "/template");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setDefaultEncoding("UTF-8");

        Set<Class> set = new HashSet<>();
        Map<String, Object> map = new HashMap<>();
        List<Class> list = new ArrayList<>();
        for (Column column : table.getColumns()) {
            if (column.getJavaType() != null) {
                set.add(column.getJavaType());
            }
        }

        list.addAll(set);
        map.put("imports", list);
        map.put("packageName", packageName);
        map.put("className", className);
        map.put("table", table);
        if (isBase) {
            map.put("isBase", "false");
        } else {
            map.put("baseClassName", PropertiesProvider.INSTANCE.getBaseClass());
        }
        try {
            Template template = cfg.getTemplate("${className}.java.ftl");
            String packageNameWithBackslash = packageName.replace(".", "\\");
            String targetPackageName =
                    String.format("src\\main\\java\\%s", packageNameWithBackslash);
            CommonUtils.enablePathExist(targetPackageName);
            String targetFilePath =
                    String.format(
                            "src\\main\\java\\%s\\%s.java",
                            packageNameWithBackslash, table.getTableCamelNameFirstUpper());
            Writer writer = new FileWriter(targetFilePath);
            template.process(map, writer);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }


    public void generateCore(Table table, String packageName, boolean isBase) {
        generateCore(table, packageName, table.getTableCamelNameFirstUpper(), isBase);
    }

    public void generateCore(Table table, String packageName) {
        generateCore(table, packageName, table.getTableCamelNameFirstUpper(), false);
    }
}
