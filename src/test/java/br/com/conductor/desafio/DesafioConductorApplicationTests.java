package br.com.conductor.desafio;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.google.gson.Gson;

@ComponentScan
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "server_port=0")
public class DesafioConductorApplicationTests {
	
	protected static final String CLIENTE_INVALIDO = "cliente";
	protected static final String CLIENTE_1 = "cliente_1";
	protected static final String CLIENTE_2 = "cliente_2";
	
	@LocalServerPort
    protected Integer portaServidor;
	
	protected HttpEntity<String> criarHttpHeaders(String clienteRequest) {
		HttpHeaders cabecalho = new HttpHeaders();
		cabecalho.set("user-permission", clienteRequest);
		return new HttpEntity<>("body", cabecalho);
	}
	
	protected HttpEntity<String> criarHttpHeaders(String clienteRequest, Object objeto) {
		HttpHeaders cabecalho = new HttpHeaders();
		cabecalho.setContentType(MediaType.APPLICATION_JSON);
		cabecalho.set("user-permission", clienteRequest);
		return new HttpEntity<>(new Gson().toJson(objeto), cabecalho);
	}
	
	protected String formatarUrl() {
		return "http://localhost:"+portaServidor.toString()+"/"+getUrlRecurso();
	}
	
	protected <T> List<T> getResultadoBody(T[] body) {
		return Arrays.stream(body).map(e -> e).collect(Collectors.toList());
	}

	protected String getUrlRecurso() {
		return "";
	}
	
}
