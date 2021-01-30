package br.com.conductor.desafio.templateloader;

import br.com.conductor.desafio.entidade.Empresa;

public class EmpresaTemplateLoader {
	
	private Empresa empresa;
	
	public EmpresaTemplateLoader() {
		empresa = new Empresa();
	}
	
	public EmpresaTemplateLoader comCnpj(String cnpj) {
		empresa.setCnpj(cnpj);
		return this;
	}

	public EmpresaTemplateLoader comEndereco(String endereco) {
		empresa.setEndereco(endereco);
		return this;
	}

	public EmpresaTemplateLoader comNomeFantasia(String nomeFanstasia) {
		empresa.setNomeFanstasia(nomeFanstasia);
		return this;
	}

	public Empresa build() {
		return empresa;
	}

}
