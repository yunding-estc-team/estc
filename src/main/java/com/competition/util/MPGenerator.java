package com.competition.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author GuoHaodong
 * @date 2019年07月30日09:42:31
 */
public class MPGenerator {
        public static void main(String[] args) {
            //读入配置文件
            Properties prop = new Properties();
            try {
                FileInputStream inputStream = new FileInputStream("mpGenerator.properties");
                prop.load(inputStream);
                inputStream.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
            // 代码生成器


            AutoGenerator mpg = new AutoGenerator();

            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            //获取当前程序路径
            String projectPath = System.getProperty("user.dir");
            gc.setOutputDir(projectPath + prop.getProperty("G.projectPath"));
            gc.setAuthor("GuoHaodong");
            gc.setEntityName(prop.getProperty("G.name.Entity"));
            gc.setMapperName(prop.getProperty("G.name.mapper"));
            gc.setXmlName(prop.getProperty("G.name.xml"));
            gc.setControllerName(prop.getProperty("G.name.controller"));
            gc.setOpen(false);
            // gc.setSwagger2(true); 实体属性 Swagger2 注解

            mpg.setGlobalConfig(gc);

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setDbType(DbType.MYSQL);
            dsc.setUrl(prop.getProperty("D.url"));
            // dsc.setSchemaName("public");
            dsc.setDriverName(prop.getProperty("D.driver"));
            dsc.setUsername(prop.getProperty("D.username"));
            dsc.setPassword(prop.getProperty("D.password"));

            mpg.setDataSource(dsc);

            // 包配置
            PackageConfig pc = new PackageConfig();
//            pc.setModuleName(scanner(prop.getProperty("P.moduleName")));
            pc.setParent(prop.getProperty("P.parent"));
            mpg.setPackageInfo(pc);

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            //设置命名格式
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
            //boolean去掉is
            strategy.setEntityBooleanColumnRemoveIsPrefix(true);
            strategy.setEntityLombokModel(true);
            strategy.setRestControllerStyle(true);
            strategy.setInclude("user");
            strategy.setControllerMappingHyphenStyle(true);
//            strategy.setTablePrefix(pc.getModuleName()   + "_");
            mpg.setStrategy(strategy);
//            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
            mpg.execute();
        }
}
