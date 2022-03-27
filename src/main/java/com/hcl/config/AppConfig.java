package com.hcl.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.hcl")
@PropertySource("classpath:db.properties")
public class AppConfig {
	@Autowired
	Environment env;

	private final String URL="DBURL";
	private final String USER="DBUSER";
	private final String PASSWORD = "DBPASSWORD";
	private final String DRIVER = "DBDRIVER";
	
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource dm= new DriverManagerDataSource();
		dm.setUrl(env.getProperty(URL));
		dm.setUsername(env.getProperty(USER));
		dm.setPassword(env.getProperty(PASSWORD));
		dm.setDriverClassName(env.getProperty(DRIVER));
		return dm;
	}
	
}
