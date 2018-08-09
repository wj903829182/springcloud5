package com.jack.mybatisplus_study1.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

/**
 * create by jack 2018/8/4
 *
 * @auther jack
 * @date: 2018/8/4 09:03
 * @Description:
 *
 */
/*
@Configuration
@MapperScan("com.jack.mybatisplus_study1.mapper")
public class MybatisConfigMetaObjOptLockConfig {

    @Bean("mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ResourceLoader resourceLoader, GlobalConfiguration globalConfiguration) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.baomidou.mybatisplus.test.h2.entity.persistent");
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                new PaginationInterceptor(),
                new PerformanceInterceptor(),
                new OptimisticLockerInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(globalConfiguration);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("-1");
        conf.setLogicNotDeleteValue("1");
        conf.setIdType(2);
        conf.setMetaObjectHandler(new H2MetaObjectHandler());
        return conf;
    }

}
*/
