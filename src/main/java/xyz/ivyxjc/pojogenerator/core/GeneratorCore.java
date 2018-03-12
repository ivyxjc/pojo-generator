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

    private Table table;
    private String packageName;
    private String className;
    private Configuration cfg;
    private Template templateWithExtends;
    private Template templateWithoutExtends;
    private Writer writer;

    public GeneratorCore() throws IOException {
        this.cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        this.cfg.setClassForTemplateLoading(Main.class, "/template");
        this.cfg.setDefaultEncoding("UTF-8");
        this.cfg.setDefaultEncoding("UTF-8");
        this.templateWithExtends = cfg.getTemplate("${className}WithExtends.java.ftl");
        this.templateWithoutExtends = cfg.getTemplate("${className}WithoutExtends.java.ftl");
    }


    public void init(Table table, String packageName, String className) throws IOException {
        this.table = table;
        this.packageName = packageName;
        this.className = className;
        String packageNameWithBackslash = packageName.replace(".", "\\");
        String targetPackageName =
                String.format("src\\main\\java\\%s", packageNameWithBackslash);
        CommonUtils.enablePathExist(targetPackageName);
        String targetFilePath =
                String.format(
                        "src\\main\\java\\%s\\%s.java",
                        packageNameWithBackslash, className);
        writer = new FileWriter(targetFilePath);
    }

    public GeneratorCore setTalbe(Table talbe) {
        this.table = talbe;
        return this;
    }

    public void generateCore(boolean haveSuperClass) throws IOException, TemplateException {

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
        if (haveSuperClass) {
            map.put("superClassName", PropertiesProvider.INSTANCE.getSuperClassName());
            templateWithExtends.process(map, writer);
        } else {
            templateWithoutExtends.process(map, writer);
        }
    }
}
