package xyz.ivyxjc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesProvider {

    private static Properties props;

    static {
        props = loadProperties("pojo-generator.properties");
    }

    private PropertiesProvider() {
    }

    public static String getBasePackageName() {
        return props.getProperty("pojo_generator.basePackageName");
    }

    public static String getCatalog() {
        return props.getProperty("pojo_generator.catalog");
    }

    public static String getSchema() {
        return props.getProperty("pojo_generator.schema");
    }

    public static String getTable() {
        return props.getProperty("pojo_generator.tableNamePattern");
    }

    public static String getTables() {
        return props.getProperty("pojo_generator.tables");
    }

    public static Properties loadProperties(String resourceName) {
        Properties properties = new Properties();
        InputStream is =
                PropertiesProvider.class.getClassLoader().getResourceAsStream(resourceName);
        if (is == null) {
            return properties;
        }
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return properties;
        }
    }
}
