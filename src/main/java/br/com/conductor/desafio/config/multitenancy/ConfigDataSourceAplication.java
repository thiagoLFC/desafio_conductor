package br.com.conductor.desafio.config.multitenancy;

import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.conductor.desafio.comum.Constantes;

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
			if(!isBDAplicacao(config.getUrl())) {
				Flyway flyway = Flyway.configure().dataSource(config.getUrl() , config.getUsername() , config.getPassword()).load();
				flyway.migrate();
			}
		}
	}

	private boolean isBDAplicacao(String url) {
		String host = url.split("/")[2];
		return Constantes.BD_DEFAULT.equals(host.split(":")[0]); 
	}

}
