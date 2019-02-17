package club.gclmit.payment.qr.other;

//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.share.common.config
 * @author: gclm
 * @date: 2019-01-10 12:49
 * @description: mybatis plus 代码生成器
 */
//public class CodeGenerator {
//
//    /**
//     * RUN THIS
//     */
//    public static void main(String[] args) {
////        1. 全局配置
//        GlobalConfig config=new GlobalConfig();
////       是否开启AR 模式
//        String projectPath = System.getProperty("user.dir");
//        config.setActiveRecord(false)
////            生成路径  这里我采用的是绝对路径  其他人可以采用相对路径试试
//                .setOutputDir(projectPath+"/src/main/java")
////            文件覆盖
//                .setFileOverride(true)
////				是否打开生成的文件
//                .setOpen(false)
////            主键生成策略
//                .setIdType(IdType.ID_WORKER_STR)
////				开启 Swagger2
//                .setSwagger2(false)
////               作者
//                .setAuthor("孤城落寞")
////             设置生成的接口的名字 首字母是否为I 一下类似
//                .setServiceName("%sService");
//
//
////      2. 数据源配置
//        DataSourceConfig dsConfig = new DataSourceConfig();
////      设置数据库类型
//        dsConfig.setDbType(DbType.H2)
//                .setDbQuery(new H2Query())
//                .setDriverName("org.h2.Driver")
//                .setUrl("jdbc:h2:file./src/main/resources/db/payment-code")
//                .setUsername("gclm")
//                .setPassword("gclm");
//
//
////      3. 策略配置
//        StrategyConfig stConfig = new StrategyConfig();
////       全局大写命名
//        stConfig.setCapitalMode(true)
////               数据库表映射到实体的命名策略
//                .setNaming(NamingStrategy.underline_to_camel)
////               表名前缀
//                .setTablePrefix("PAY_")
//                .setEntityLombokModel(true)
////               用于生成的表 传入的值是同一个集合
//                .setInclude("PAY_QR","PAY_USER");
//
////      4. 包名策略配置
//        PackageConfig pkConfig = new PackageConfig();
////      设置全局包名
//        pkConfig.setParent("club.gclmit.qr")
//                .setMapper("mapper")
//                .setService("service")
//                .setController("controller")
//                .setEntity("pojo");
//
////      5. 整合配置
//        AutoGenerator ag = new AutoGenerator();
//        ag.setGlobalConfig(config)
//                .setDataSource(dsConfig)
//                .setPackageInfo(pkConfig)
//                .setStrategy(stConfig);
////      6. 执行
//        ag.execute();
//    }
//}