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
import br.com.conductor.desafio.entidade.Empresa;
import br.com.conductor.desafio.templateloader.EmpresaTemplateLoader;

public class EmpresaControllerTest extends DesafioConductorApplicationTests {
	
	private static final int QTD_EMPRESAS = 5;
	private static final int QTD_EMPRESA_UNICA = 1;

	@Test 
	public void testConsultarTodasEmpresasSemDados() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Empresa.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}
	
	@Test 
	public void testConsultarTodasEmpresasComClienteRequestInvalido() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_INVALIDO), Empresa.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
		}
	}
	
	@Test
	public void testConsultarTodasEmpresasComDados() {
		criarEmpresas(QTD_EMPRESAS , CLIENTE_1);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Empresa[]> retorno = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Empresa[].class);
		List<Empresa> empresas = getResultadoBody(retorno.getBody());
		assertEquals(empresas.size(), QTD_EMPRESAS);
		removerEmpresas(empresas , CLIENTE_1);
	}
	
	@Test
	public void testConsultarClienteUnico() {
		RestTemplate restTemplate = new RestTemplate();
		criarEmpresas(QTD_EMPRESA_UNICA, CLIENTE_1);
		ResponseEntity<Empresa[]> retornoEmpresas = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Empresa[].class);
		List<Empresa> empresas = getResultadoBody(retornoEmpresas.getBody());
		assertEquals(empresas.size(), QTD_EMPRESA_UNICA);
		
		ResponseEntity<Empresa> retornoEmpresa = restTemplate.exchange(formatarUrl() + "/"+empresas.get(0).getId(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Empresa.class);
		assertNotNull(retornoEmpresa.getBody());
		removerEmpresas(empresas, CLIENTE_1);
	}
	
	@Test
	public void testConsultarEmpresaComClienteRequestDiferente() {
		RestTemplate restTemplate = new RestTemplate();
		try {
			criarEmpresas(QTD_EMPRESAS , CLIENTE_1);
			restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_2), Empresa[].class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
			ResponseEntity<Empresa[]> retornoEmpresas = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Empresa[].class);
			List<Empresa> empresas = getResultadoBody(retornoEmpresas.getBody());
			assertEquals(empresas.size(), QTD_EMPRESAS);
			removerEmpresas(empresas , CLIENTE_1);
		}
	}
	
	@Test
	public void testCadastrarEmpresaJaExistente() {
		RestTemplate restTemplate = new RestTemplate();
		Empresa empresa = new EmpresaTemplateLoader().comCnpj("1245789654200").comEndereco("QE 25 conjunto B casa").comNomeFantasia("Nome fantasia ").build();
		restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , empresa), Empresa.class);
		
		try {
			restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , empresa), Empresa.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			ResponseEntity<Empresa[]> retornoEmpresas = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Empresa[].class);
			List<Empresa> empresas = getResultadoBody(retornoEmpresas.getBody());
			assertEquals(empresas.size(), QTD_EMPRESA_UNICA);
			removerEmpresas(empresas , CLIENTE_1);
		}
	}
	
	@Test
	public void testCadastrarEmpresaComClienteRequestInvalido() {
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			Empresa empresa = new EmpresaTemplateLoader().comCnpj("1245789654200").comEndereco("QE 25 conjunto B casa").comNomeFantasia("Nome fantasia ").build();
			restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_INVALIDO , empresa), Empresa.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
		}
	}
	
	@Test
	public void testAtualizarEmpresaSucesso() {
		String novoNomeFantasia = "Um novo nome";
		String novoCnpj = "99966655587";
		String novoEndereco = "novo@novo.com";
		
		RestTemplate restTemplate = new RestTemplate();
		Empresa empresa = new EmpresaTemplateLoader().comCnpj("1245789654200").comEndereco("QE 25 conjunto B casa").comNomeFantasia("Nome fantasia ").build();
		restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , empresa), Empresa.class);
		
		ResponseEntity<Empresa[]> retornoEmpresaCadastro = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Empresa[].class);
		List<Empresa> empresaCadastro = getResultadoBody(retornoEmpresaCadastro.getBody());
		assertEquals(empresaCadastro.size(), QTD_EMPRESA_UNICA);
		assertEquals(empresaCadastro.get(0).getNomeFanstasia(), empresa.getNomeFanstasia());
		assertEquals(empresaCadastro.get(0).getCnpj(), empresa.getCnpj());
		assertEquals(empresaCadastro.get(0).getEndereco(), empresa.getEndereco());

		Empresa empresasAtualizar = empresaCadastro.get(0);
		empresasAtualizar.setNomeFanstasia(novoNomeFantasia);
		empresasAtualizar.setCnpj(novoCnpj);
		empresasAtualizar.setEndereco(novoEndereco);
		
		restTemplate.exchange(formatarUrl() + "/" + empresasAtualizar.getId(), HttpMethod.PUT, criarHttpHeaders(CLIENTE_1 , empresasAtualizar), Empresa.class);
		
		ResponseEntity<Empresa[]> retornoEmpresasAtualizar = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Empresa[].class);
		List<Empresa> empresaAtualizar = getResultadoBody(retornoEmpresasAtualizar.getBody());
		assertEquals(empresaAtualizar.size(), QTD_EMPRESA_UNICA);
		assertEquals(empresaAtualizar.get(0).getNomeFanstasia(), novoNomeFantasia);
		assertEquals(empresaAtualizar.get(0).getCnpj(), novoCnpj);
		assertEquals(empresaAtualizar.get(0).getEndereco(), novoEndereco);
		
		removerEmpresas(empresaAtualizar , CLIENTE_1);
	}
	
	@Test
	public void testAtualizarEmpresaComClienteRequestInvalido() {
		RestTemplate restTemplate = new RestTemplate();
		Empresa empresa = new EmpresaTemplateLoader().comCnpj("1245789654200").comEndereco("QE 25 conjunto B casa").comNomeFantasia("Nome fantasia ").build();
		restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , empresa), Empresa.class);
		
		ResponseEntity<Empresa[]> retornoEmpresas = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Empresa[].class);
		List<Empresa> empresasCadastro = getResultadoBody(retornoEmpresas.getBody());
		assertEquals(empresasCadastro.size(), QTD_EMPRESA_UNICA);
		
		Empresa empresaAtualizar = empresasCadastro.get(0);
		empresaAtualizar.setNomeFanstasia("Novo nome fantasia");
		
		try {
			restTemplate.exchange(formatarUrl() + "/" + empresaAtualizar.getId(), HttpMethod.PUT, criarHttpHeaders(CLIENTE_2 , empresaAtualizar), Empresa.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			
			ResponseEntity<Empresa[]> retornoEmpresasCadastradas = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Empresa[].class);
			List<Empresa> empresasJaCadastras = getResultadoBody(retornoEmpresasCadastradas.getBody());
			assertEquals(empresasJaCadastras.size(), QTD_EMPRESA_UNICA);
			removerEmpresas(empresasJaCadastras, CLIENTE_1);
		}
	}
	
	@Test
	public void testRemoverEmpresaComClienteRequestInvalido() {
		RestTemplate restTemplate = new RestTemplate();
		Empresa empresa = new EmpresaTemplateLoader().comCnpj("1245789654200").comEndereco("QE 25 conjunto B casa").comNomeFantasia("Nome fantasia ").build();
		restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(CLIENTE_1 , empresa), Empresa.class);
		
		ResponseEntity<Empresa[]> retornoEmpresas = restTemplate.exchange(formatarUrl(), HttpMethod.GET, criarHttpHeaders(CLIENTE_1), Empresa[].class);
		List<Empresa> empresasCadastro = getResultadoBody(retornoEmpresas.getBody());
		assertEquals(empresasCadastro.size(), QTD_EMPRESA_UNICA);
		Empresa empresaRemover = empresasCadastro.get(0);
		
		try {
			restTemplate.exchange(formatarUrl() + "/" + empresaRemover.getId(), HttpMethod.DELETE, criarHttpHeaders(CLIENTE_2), Empresa.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			removerEmpresas(empresasCadastro, CLIENTE_1);
		}
	}
	
	private void removerEmpresas(List<Empresa> empresas, String clienteRequest) {
		RestTemplate restTemplate = new RestTemplate();
		for (Empresa empresa : empresas) {
			restTemplate.exchange(formatarUrl() + "/" + empresa.getId(), HttpMethod.DELETE, criarHttpHeaders(clienteRequest), Empresa.class);
		}
	}

	private void criarEmpresas(int qtdEmpresas , String clienteRequest) {
		RestTemplate restTemplate = new RestTemplate();
		for (int i = 0; i < qtdEmpresas; i++) {
			Empresa empresa = new EmpresaTemplateLoader()
					.comCnpj("1245789654200"+i)
					.comEndereco("QE 25 conjunto B casa"+i)
					.comNomeFantasia("Nome fantasia "+i).build();
			restTemplate.exchange(formatarUrl(), HttpMethod.POST, criarHttpHeaders(clienteRequest , empresa), Empresa.class);
		}
	}

	@Override
	protected String getUrlRecurso() {
		return "empresas";
	}

}
