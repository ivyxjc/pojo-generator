<#--<#include "macro.include"/>-->
<#--<#include "java_copyright.include">-->
<#--<#assign className = table.className>-->
<#--<#assign classNameLower = className?uncap_first>-->
package ${packageName};

<@generateImports/>

public class ${className} {

<#list table.columns as column>
	@DBColumn(javaType = ${column.javaType.simpleName}.class,columnType = ${column.columnDbTypeStr},columnName = "${column.columnName}")
	private ${column.javaType.simpleName} ${column.columnCamelName};
</#list>
<@generateGetterSetter/>
}

<#macro generateGetterSetter>
    <#list table.columns as column>
	public void set${column.columnCamelNameWithFirstUpper}(${column.javaType.simpleName} value) {
		this.${column.columnCamelName} = value;
	}

	public ${column.javaType.simpleName} get${column.columnCamelNameWithFirstUpper}() {
		return this.${column.columnCamelName};
	}
    </#list>
</#macro>

<#macro generateTableAlias>
    <#if isBase??>

    <#else>
	public class ${className} extends ${baseClassName} {
    </#if>
</#macro>

<#macro generateImports>
import xyz.ivyxjc.pojogenerator.model.DBColumn;
import java.sql.Types;
    <#list imports as import>
import ${import.name};
    </#list>
</#macro>