<#--<#include "macro.include"/>-->
<#--<#include "java_copyright.include">-->
<#--<#assign className = table.className>-->
<#--<#assign classNameLower = className?uncap_first>-->
package ${packageName};

<@generateImports/>

public class ${className} {

	//alias
	public static final String TABLE_ALIAS = "${className}";

	//columns START
<#list table.columns as column>
	private ${column.javaType.simpleName} ${column.columnCamelName};
</#list>
	//columns END
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



<#macro generateImports>
    <#list imports as import>
import ${import.name};
    </#list>
</#macro>