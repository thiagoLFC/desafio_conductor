package br.com.conductor.desafio.controller.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Desafio Conductor")
                		.description("Serviços disponíveis do sistema Desafio Conductor utilizando a especificação OpenAPI 3.0")
                		.version("1.0.0")
        		);
    }

}
