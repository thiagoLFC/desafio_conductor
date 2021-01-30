package br.com.conductor.desafio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.conductor.desafio.DesafioConductorApplicationTests;
import br.com.conductor.desafio.entidade.Cliente;
import br.com.conductor.desafio.templateloader.ClienteTemplateLoader;

public class ClienteControllerTest extends DesafioConductorApplicationTests {
	
	private static final int QTD_CLIENTES = 5;
	private static final int QTD_CLIENTE_UNICO = 1;

	@Test 
	public void testConsultarTodosClientesSemDados() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}
	
	@Test 
	public void testConsultarTodosClientesComClienteRequestInvalido() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_INVALIDO), Cliente.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
		}
	}
	
	@Test
	public void testConsultarTodosClientesComDados() {
		criarClientes(QTD_CLIENTES , CLIENTE_1);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Cliente[]> retorno = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
		List<Cliente> clientes = getResultadoBody(retorno.getBody());
		assertEquals(clientes.size(), QTD_CLIENTES);
		removerClientes(clientes , CLIENTE_1);
	}
	
	@Test
	public void testConsultarClienteUnico() {
		RestTemplate restTemplate = new RestTemplate();
		criarClientes(QTD_CLIENTE_UNICO, CLIENTE_1);
		ResponseEntity<Cliente[]> retornoClientes = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
		List<Cliente> clientes = getResultadoBody(retornoClientes.getBody());
		assertEquals(clientes.size(), QTD_CLIENTE_UNICO);
		
		ResponseEntity<Cliente> retornoCliente = restTemplate.exchange(formatarUrl() + "/"+clientes.get(0).getId(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente.class);
		assertNotNull(retornoCliente.getBody());
		removerClientes(clientes, CLIENTE_1);
	}
	
	@Test
	public void testConsultarClienteComClienteRequestDiferente() {
		RestTemplate restTemplate = new RestTemplate();
		try {
			criarClientes(QTD_CLIENTES , CLIENTE_1);
			restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_2), Cliente[].class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
			ResponseEntity<Cliente[]> retornoClientes = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
			List<Cliente> clientes = getResultadoBody(retornoClientes.getBody());
			assertEquals(clientes.size(), QTD_CLIENTES);
			removerClientes(clientes , CLIENTE_1);
		}
	}
	
	@Test
	public void testCadastrarClienteJaExistente() {
		RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new ClienteTemplateLoader().comCpf("02154789528").comEmail("email@email.com").comNome("Nome cliente").build();
		restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , cliente), Cliente.class);
		
		try {
			restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , cliente), Cliente.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			ResponseEntity<Cliente[]> retornoClientes = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
			List<Cliente> clientes = getResultadoBody(retornoClientes.getBody());
			assertEquals(clientes.size(), QTD_CLIENTE_UNICO);
			removerClientes(clientes , CLIENTE_1);
		}
	}
	
	@Test
	public void testCadastrarClienteEmailInvalido() {
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			Cliente cliente = new ClienteTemplateLoader().comCpf("02154789528").comEmail("emailemail.com").comNome("Nome cliente").build();
			restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , cliente), Cliente.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			try {
				restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
			} catch (HttpClientErrorException e1) {
				assertEquals(HttpStatus.NOT_FOUND, e1.getStatusCode());
			}
		}
	}
	
	@Test
	public void testCadastrarClienteComClienteRequestInvalido() {
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			Cliente cliente = new ClienteTemplateLoader().comCpf("02154789528").comEmail("emailemail.com").comNome("Nome cliente").build();
			restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_INVALIDO , cliente), Cliente.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
		}
	}
	
	@Test
	public void testAtualizarClienteSucesso() {
		String novoNome = "Um novo nome";
		String novoCpf = "99966655587";
		String novoEmail = "novo@novo.com";
		
		RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new ClienteTemplateLoader().comCpf("02154789528").comEmail("email@email.com").comNome("Nome cliente").build();
		restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , cliente), Cliente.class);
		
		ResponseEntity<Cliente[]> retornoClientesCadastro = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
		List<Cliente> clientesCadastro = getResultadoBody(retornoClientesCadastro.getBody());
		assertEquals(clientesCadastro.size(), QTD_CLIENTE_UNICO);
		assertEquals(clientesCadastro.get(0).getNome(), cliente.getNome());
		assertEquals(clientesCadastro.get(0).getCpf(), cliente.getCpf());
		assertEquals(clientesCadastro.get(0).getEmail(), cliente.getEmail());

		Cliente clienteAtualizar = clientesCadastro.get(0);
		clienteAtualizar.setNome(novoNome);
		clienteAtualizar.setCpf(novoCpf);
		clienteAtualizar.setEmail(novoEmail);
		
		restTemplate.exchange(formatarUrl() + "/" + clienteAtualizar.getId(), HttpMethod.PUT, criarHttpHeaders(CLIENTE_1 , clienteAtualizar), Cliente.class);
		
		ResponseEntity<Cliente[]> retornoClientesAtualizar = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
		List<Cliente> clientesAtualizar = getResultadoBody(retornoClientesAtualizar.getBody());
		assertEquals(clientesAtualizar.size(), QTD_CLIENTE_UNICO);
		assertEquals(clientesAtualizar.get(0).getNome(), novoNome);
		assertEquals(clientesAtualizar.get(0).getCpf(), novoCpf);
		assertEquals(clientesAtualizar.get(0).getEmail(), novoEmail);
		
		removerClientes(clientesAtualizar , CLIENTE_1);
	}
	
	@Test
	public void testAtualizarClienteEmailInvalido() {
		RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new ClienteTemplateLoader().comCpf("02154789528").comEmail("email@email.com").comNome("Nome cliente").build();
		restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , cliente), Cliente.class);
		
		ResponseEntity<Cliente[]> retornoClientes = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
		List<Cliente> clientesCadastro = getResultadoBody(retornoClientes.getBody());
		assertEquals(clientesCadastro.size(), QTD_CLIENTE_UNICO);
		
		Cliente clienteAtualizar = clientesCadastro.get(0);
		clienteAtualizar.setEmail("123.com.br");
		
		try {
			restTemplate.exchange(formatarUrl() + "/" + clienteAtualizar.getId(), HttpMethod.PUT, criarHttpHeaders(CLIENTE_1 , clienteAtualizar), Cliente.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			
			ResponseEntity<Cliente[]> retornoClientesCadastrados = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
			List<Cliente> clientesJaCadastro = getResultadoBody(retornoClientesCadastrados.getBody());
			assertEquals(clientesJaCadastro.size(), QTD_CLIENTE_UNICO);
			
			assertEquals(clientesJaCadastro.get(0).getEmail(), cliente.getEmail());
			removerClientes(clientesJaCadastro, CLIENTE_1);
		}
	}
	
	@Test
	public void testAtualizarClienteComClienteRequestInvalido() {
		RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new ClienteTemplateLoader().comCpf("02154789528").comEmail("email@email.com").comNome("Nome cliente").build();
		restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , cliente), Cliente.class);
		
		ResponseEntity<Cliente[]> retornoClientes = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
		List<Cliente> clientesCadastro = getResultadoBody(retornoClientes.getBody());
		assertEquals(clientesCadastro.size(), QTD_CLIENTE_UNICO);
		
		Cliente clienteAtualizar = clientesCadastro.get(0);
		clienteAtualizar.setEmail("123@123.com.br");
		
		try {
			restTemplate.exchange(formatarUrl() + "/" + clienteAtualizar.getId(), HttpMethod.PUT, criarHttpHeaders(CLIENTE_2 , clienteAtualizar), Cliente.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			
			ResponseEntity<Cliente[]> retornoClientesCadastrados = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
			List<Cliente> clientesJaCadastro = getResultadoBody(retornoClientesCadastrados.getBody());
			assertEquals(clientesJaCadastro.size(), QTD_CLIENTE_UNICO);
			removerClientes(clientesJaCadastro, CLIENTE_1);
		}
	}
	
	@Test
	public void testRemoverClienteComClienteRequestInvalido() {
		RestTemplate restTemplate = new RestTemplate();
		Cliente cliente = new ClienteTemplateLoader().comCpf("02154789528").comEmail("email@email.com").comNome("Nome cliente").build();
		restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , cliente), Cliente.class);
		
		ResponseEntity<Cliente[]> retornoClientes = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Cliente[].class);
		List<Cliente> clientesCadastro = getResultadoBody(retornoClientes.getBody());
		assertEquals(clientesCadastro.size(), QTD_CLIENTE_UNICO);
		Cliente clienteRemover = clientesCadastro.get(0);
		
		try {
			restTemplate.exchange(formatarUrl() + "/" + clienteRemover.getId(), HttpMethod.DELETE, criarHttpHeaders(CLIENTE_2), Cliente.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			removerClientes(clientesCadastro, CLIENTE_1);
		}
	}
	
	private void removerClientes(List<Cliente> clientes, String clienteRequest) {
		RestTemplate restTemplate = new RestTemplate();
		for (Cliente cliente : clientes) {
			restTemplate.exchange(formatarUrl() + "/" + cliente.getId(), HttpMethod.DELETE, criarHttpHeaders(clienteRequest), Cliente.class);
		}
	}

	private void criarClientes(int qtdClientes , String clienteRequest) {
		RestTemplate restTemplate = new RestTemplate();
		for (int i = 0; i < qtdClientes; i++) {
			Cliente cliente = new ClienteTemplateLoader()
					.comCpf("0215478952"+i)
					.comEmail("email"+i+"@email.com")
					.comNome("Nome cliente "+i).build();
			restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(clienteRequest , cliente), Cliente.class);
		}
	}

	@Override
	protected String getUrlRecurso() {
		return "clientes";
	}

}
