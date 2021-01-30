package br.com.conductor.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import br.com.conductor.desafio.config.multitenancy.ConfigApplicationsDatabase;

@ComponentScan
@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties(ConfigApplicationsDatabase.class)
public class DesafioConductorApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioConductorApplication.class, args);
	}

}
