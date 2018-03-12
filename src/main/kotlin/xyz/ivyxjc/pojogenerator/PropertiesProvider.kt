package xyz.ivyxjc.pojogenerator

import java.util.*

object PropertiesProvider {

    private var props: Properties? = null

    val packageName: String
        get() = props!!.getProperty("pojo_generator.packageName")

    val schema: String
        get() = props!!.getProperty("pojo_generator.schema")

    val table: String
        get() = props!!.getProperty("pojo_generator.tableNamePattern")

    val catalog: String?
        get() = props?.getProperty("pojo_generator.catalog")

    val tables: String?
        get() = props?.getProperty("pojo_generator.tables")

    val baseClass: String?
        get() = props?.getProperty("pojo_generator.baseClassName")

    val baseColumnNames: String?
        get() = props?.getProperty("pojo_generator.baseColumnNames")

    init {
        props = loadProperties("pojo-generator.properties")
    }

    fun loadProperties(resourceName: String): Properties {
        val properties = Properties()
        ClassLoader.getSystemResourceAsStream(resourceName)?.use { content -> properties.load(content);return properties }
                ?: return properties
    }
}
