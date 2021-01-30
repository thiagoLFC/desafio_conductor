package br.com.conductor.desafio.templateloader;

import br.com.conductor.desafio.entidade.Cliente;

public class ClienteTemplateLoader {
	
	private Cliente cliente;
	
	public ClienteTemplateLoader() {
		cliente = new Cliente();
	}
	
	public ClienteTemplateLoader comCpf(String cpf) {
		cliente.setCpf(cpf);
		return this;
	}

	public ClienteTemplateLoader comEmail(String email) {
		cliente.setEmail(email);
		return this;
	}

	public ClienteTemplateLoader comNome(String nome) {
		cliente.setNome(nome);
		return this;
	}

	public Cliente build() {
		return cliente;
	}

}
