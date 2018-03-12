package xyz.ivyxjc.pojogenerator

import java.util.*

object PropertiesProvider {

    private var props: Properties? = null

    val packageName: String
        get() = props!!.getProperty("pojo_generator.packageName")

    val schema: String
        get() = props!!.getProperty("pojo_generator.schema")

    val tableNamePattern: String
        get() = props!!.getProperty("pojo_generator.tableNamePattern")

    val catalog: String?
        get() = props?.getProperty("pojo_generator.catalog")

    val superClassName: String?
        get() = props?.getProperty("pojo_generator.superClassName")

    val baseColumnNames: Array<String>?
        get() = props?.getProperty("pojo_generator.baseColumnNames")?.split(",")?.toTypedArray()

    val specificClassName: String?
        get() = props?.getProperty("pojo_generator.specificClassName")

    init {
        props = loadProperties("pojo-generator.properties")
    }

    fun loadProperties(resourceName: String): Properties {
        val properties = Properties()
        ClassLoader.getSystemResourceAsStream(resourceName)?.use { content -> properties.load(content);return properties }
                ?: return properties
    }
}
