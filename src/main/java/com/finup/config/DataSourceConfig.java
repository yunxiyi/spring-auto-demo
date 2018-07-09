package com.finup.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangrongchao on 2018/5/25.
 * @version 1.0
 */
@Configuration
public class DataSourceConfig {

    @Bean(destroyMethod = "shutdown")
    public DataSource createDataSource(){
        return new HikariDataSource(dataSourceProperties());
    }

    @ConfigurationProperties(prefix = "hikari")
    @Bean
    public HikariConfig dataSourceProperties(){
        return new HikariConfig();
    }
}
