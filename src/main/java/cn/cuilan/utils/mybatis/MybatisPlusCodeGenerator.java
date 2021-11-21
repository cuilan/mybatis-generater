package cn.cuilan.utils.mybatis;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * MybatisPlus 代码生成器
 *
 * @author zhang.yan
 * @date 2021/10/13
 */
public class MybatisPlusCodeGenerator {

    // ------- mysql 配置 ----------------------

    // mysql连接
    public static String url;

    // 用户名
    public static String username;

    // 密码
    public static String password;

    // 驱动名称
    public static String driver;

    // 设置表前缀
    public static String table_prefix;

    // ------- 代码生成配置 ----------------------

    // 项目目录，默认当前目录
    public static String project_path;

    // 输出目录
    public static String output_dir;

    // 包名
    public static String package_name;

    // 作者名称
    public static String author;

    // 是否开启lombok注解
    public static boolean enable_lombok = true;

    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new RuntimeException("请输入正确的" + tip + "！");
    }

    public static void generator() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(output_dir);
        gc.setAuthor(author);
        gc.setOpen(false);
        // 实体属性 Swagger2 注解
        // gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driver);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        final PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent(package_name);
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        // 表名，下划线 -> 驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字段名，下划线 -> 驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(enable_lombok);

        strategy.setRestControllerStyle(true);
        // 公共父类
        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(table_prefix);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    public static void loadProperties() {
        InputStream in;
        Properties properties = new Properties();
        try {
            in = new BufferedInputStream(new FileInputStream(new File("config.properties")));
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            table_prefix = properties.getProperty("table_prefix");
            project_path = properties.getProperty("project_path");
            output_dir = project_path + "/src/main/java";
            package_name = properties.getProperty("package_name");
            author = properties.getProperty("author");
            enable_lombok = Boolean.parseBoolean(properties.getProperty("enable_lombok"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadProperties();
        generator();
    }

}
