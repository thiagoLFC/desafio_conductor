package br.com.conductor.desafio.config.multitenancy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Classe para carregar as configuracoes de banco do arquivo application.properties
 * @author thiag
 *
 */
@ConfigurationProperties(prefix = "db")
public class ConfigApplicationsDatabase {
	
    private Map<String, ConfigApplicationDatabase> configurations = new HashMap<>();

    public Map<Object, Object> createTargetDataSources() {
        Map<Object, Object> result = new HashMap<>();
        configurations.forEach((key, value) ->  result.put(key, value.createDataSource()));
        return result;
    }

	public Map<String, ConfigApplicationDatabase> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(Map<String, ConfigApplicationDatabase> configurations) {
		this.configurations = configurations;
	}
}
