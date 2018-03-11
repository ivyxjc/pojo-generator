package xyz.ivyxjc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;
import xyz.ivyxjc.model.Column;
import xyz.ivyxjc.model.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@Service
public class GeneratorCore {

    public void generateCore(Table table, String packageName) {
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
        map.put("className", table.getTableCamelNameFirstUpper());
        map.put("table", table);
        try {
            Template template = cfg.getTemplate("${className}.java.ftl");
            String packageNameWithBackslash = packageName.replace(".", "\\");
            Writer writer =
                    new FileWriter(
                            String.format(
                                    "src\\main\\java\\%s\\%s.java",
                                    packageNameWithBackslash, table.getTableCamelNameFirstUpper()));
            template.process(map, writer);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
