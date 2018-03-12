//package xyz.ivyxjc.pojogenerator.core;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import xyz.ivyxjc.pojogenerator.PropertiesProvider;
//import xyz.ivyxjc.pojogenerator.model.Table;
//import xyz.ivyxjc.pojogenerator.utils.CommonUtils;
//
//@Service
//public class BaseMethodGenerate {
//
//    @Autowired
//    GeneratorCore mGeneratorCore;
//
//    public Table handle(Table table) {
//        String baseClass = PropertiesProvider.INSTANCE.getSuperClassName();
//        String baseColumnNames = PropertiesProvider.INSTANCE.getBaseColumnNames();
//        String basePackageName = PropertiesProvider.INSTANCE.getPackageName();
//        if (StringUtils.isEmpty(baseClass) || StringUtils.isEmpty(baseColumnNames)) {
//            return table;
//        }
//        String[] baseColumnNamesArray = baseColumnNames.split(",");
//        Table baseMethodTable = CommonUtils.initOneTable(table);
//        baseMethodTable.setSpecificClassName(baseClass);
//        for (int i = 0; i < baseColumnNamesArray.length; i++) {
//            baseMethodTable.addColumn(table.getColumn(baseColumnNamesArray[i]));
//            table.removeColumn(baseColumnNamesArray[i]);
//        }
//        mGeneratorCore.generateCore(baseMethodTable, basePackageName, baseClass, true);
//        return table;
//    }
//}
