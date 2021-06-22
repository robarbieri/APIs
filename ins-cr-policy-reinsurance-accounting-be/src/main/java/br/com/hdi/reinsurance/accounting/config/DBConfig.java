package br.com.hdi.reinsurance.accounting.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBConfig {

	@Bean
	@Primary
	@ConfigurationProperties(prefix="app.datasource.hdi")
    public DataSource dataSourceHdi() {
		return DataSourceBuilder.create().build();
    }
	
}
