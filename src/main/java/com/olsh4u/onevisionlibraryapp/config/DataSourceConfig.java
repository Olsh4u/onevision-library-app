package com.olsh4u.onevisionlibraryapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource postgresDataSource(PostgresProperties postgresProperties) {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(postgresProperties.getUrl());
        driverManagerDataSource.setUsername(postgresProperties.getUsername());
        driverManagerDataSource.setPassword(postgresProperties.getPassword());
        driverManagerDataSource.setDriverClassName(postgresProperties.getDriver());
        return driverManagerDataSource;
    }


    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
