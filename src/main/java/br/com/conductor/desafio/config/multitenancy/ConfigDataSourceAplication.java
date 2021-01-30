package br.com.conductor.desafio.config.multitenancy;

import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Classe para configurar a criacao dos datasources e sincronizar a criacao das estruturas do banco de dados
 * @author thiag
 *
 */
@Component
public class ConfigDataSourceAplication {
	
	@Autowired
	private ConfigApplicationsDatabase databaseConfigurations;
	
	@Bean
    public DataSource dataSource() {
		ConfigBDRoutingDataSource dataSource = new ConfigBDRoutingDataSource();
        dataSource.setTargetDataSources(databaseConfigurations.createTargetDataSources());
        return dataSource;
	}

	/**
	 * Metodo para sincronizar a execucao 
	 */
	@PostConstruct
	public void migrate() {
		for (Entry<String, ConfigApplicationDatabase> dataSource : databaseConfigurations.getConfigurations().entrySet()) {
			ConfigApplicationDatabase config = dataSource.getValue();
			if(!config.isCentral()) {
				Flyway flyway = Flyway.configure().dataSource(config.getUrl() , config.getUsername() , config.getPassword()).load();
				flyway.migrate();
			}
		}
	}
}
