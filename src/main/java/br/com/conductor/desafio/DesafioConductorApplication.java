package br.com.conductor.desafio;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import br.com.conductor.desafio.config.multitenancy.ConfigApplicationsDatabase;
import br.com.conductor.desafio.config.multitenancy.ConfigBDRoutingDataSource;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@EnableConfigurationProperties(ConfigApplicationsDatabase.class)
public class DesafioConductorApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioConductorApplication.class, args);
	}
	
	@Autowired
	private ConfigApplicationsDatabase databaseConfigurations;
	
	@Bean
    public DataSource dataSource() {
		ConfigBDRoutingDataSource dataSource = new ConfigBDRoutingDataSource();
        dataSource.setTargetDataSources(databaseConfigurations.createTargetDataSources());
        return dataSource;
    }
	
}
