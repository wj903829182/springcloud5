package com.jack.mybatisplus_study1.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

/**
 * create by jack 2018/8/4
 *
 * @auther jack
 * @date: 2018/8/4 09:17
 * @Description:
 */
@Configuration
//@MapperScan("com.jack.mybatisplus_study1.mapper*")
public class MybatisPlusConfig {
    /*@Bean("mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ResourceLoader resourceLoader) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.jack.mybatisplus_study1.entity");
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                new PaginationInterceptor(),
                new PerformanceInterceptor(),
                new OptimisticLockerInterceptor()
        });
        //sqlSessionFactory.setGlobalConfig(globalConfiguration);
        //sqlSessionFactory.setGlobalConfig();
        //GlobalConfiguration globalConfiguration =

        return sqlSessionFactory.getObject();
    }*/


    /**
     * 相当于顶部的：
     * {@code @MapperScan("com.baomidou.springboot.mapper*")}
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.jack.mybatisplus_study1.mapper*");
        return scannerConfigurer;
    }


    /**
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /*@Bean
    public GlobalConfig globalConfiguration() {
        GlobalConfig conf = new GlobalConfig();
        return conf;
    }*/


}
