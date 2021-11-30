package com.example;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("www");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        mpg.setGlobalConfig(gc);


        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://119.23.222.86:3306/oa_manager?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        //数据库连接用户名
        dsc.setUsername("oa_mamager");
        //数据库连接密码
        dsc.setPassword("ruanjiangongcheng");
        mpg.setDataSource(dsc);


        //包配置
        PackageConfig pc = new PackageConfig();
        //父包模块名
        pc.setModuleName("generator");
        //父包名，如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent("com.example");
//        pc.setController("controller");
//        pc.setService("service");
//        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");

        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        mpg.setStrategy(strategy);

        mpg.execute();
    }
}
