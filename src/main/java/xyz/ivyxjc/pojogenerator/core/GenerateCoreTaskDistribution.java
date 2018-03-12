package xyz.ivyxjc.pojogenerator.core;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.ivyxjc.pojogenerator.PropertiesProvider;
import xyz.ivyxjc.pojogenerator.model.Column;
import xyz.ivyxjc.pojogenerator.model.Table;
import xyz.ivyxjc.pojogenerator.utils.CommonUtils;

import java.io.IOException;
import java.util.Optional;

@Service
public class GenerateCoreTaskDistribution {

    @Autowired
    GeneratorCore mGeneratorCore;

    public void taskDistribution(Table table) throws IOException, TemplateException {
        String packageName = PropertiesProvider.INSTANCE.getPackageName();
        String className = Optional.ofNullable(PropertiesProvider.INSTANCE.getSpecificClassName()).orElse(table.getTableCamelNameFirstUpper());
        String superClassName = PropertiesProvider.INSTANCE.getSuperClassName();
        String[] baseColumnNames = PropertiesProvider.INSTANCE.getBaseColumnNames();

        if (superClassName == null || baseColumnNames == null || baseColumnNames.length == 0) {
            mGeneratorCore.init(table, packageName, className);
            mGeneratorCore.generateCore(false);
        } else {
            Table baseTable = CommonUtils.initOneTable(table);
            for (String columnName : baseColumnNames) {
                Column column = table.getColumn(columnName);
                /**
                 * if table do not have column stated in baseColumns, it will not create superClass.
                 */
                if (table.removeColumn(columnName) == null) {
                    table.putAllColumns(baseTable);
                    baseTable = null;
                    break;
                } else {
                    baseTable.addColumn(column);
                }
            }
            mGeneratorCore.init(table, packageName, className);
            if (baseTable != null) {
                mGeneratorCore.generateCore(true);
                mGeneratorCore.init(baseTable, packageName, superClassName);
                mGeneratorCore.generateCore(false);
            } else {
                mGeneratorCore.generateCore(false);
            }


        }
    }
}
