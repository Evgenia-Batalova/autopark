package com.batalova.autopark.context;

import com.batalova.autopark.controller.AutoparkController;
import com.batalova.autopark.jdbc.AutoparkDao;
import com.batalova.autopark.jdbc.AutoparkDaoImpl;
import com.batalova.autopark.services.AutoparkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AutoparkContext {

    @Bean
    public DataSource dataSourceProperties(
            @Value("${autopark.jdbc.url}")
            String url,
            @Value("${autopark.jdbc.username}")
            String username,
            @Value("${autopark.jdbc.password}")
            String password
    )
    {
        return new DriverManagerDataSource(url, username, password);
    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public AutoparkDao autoparkJdbc(JdbcTemplate jdbcTemplate) {
        return new AutoparkDaoImpl(jdbcTemplate);
    }

    @Bean
    public AutoparkService autoparkService(AutoparkDao autoparkDao) {
        return new AutoparkService(autoparkDao);
    }

}
