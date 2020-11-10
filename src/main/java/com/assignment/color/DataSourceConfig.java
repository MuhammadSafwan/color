package com.assignment.color;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author Safwan
 */
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "h2DataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource getH2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mySQLDataSource")
    @ConfigurationProperties(prefix="spring.second-datasource")
    public DataSource getMySQLDataSource() {
        return DataSourceBuilder.create().build();
    }
}
