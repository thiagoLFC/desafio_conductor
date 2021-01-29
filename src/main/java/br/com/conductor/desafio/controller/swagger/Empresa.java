package br.com.conductor.desafio.controller.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

public class Empresa {

	@Schema(description = "Identificador da empresa.", example = "1", required = true)
	private Integer id;

	@Schema(description = "Nome fantasia da empresa.", example = "Nome fantasia da emppresa", required = true)
	private String nomeFanstasia;

	@Schema(description = "CNPJ da empresa.", example = "29669597770553", required = true)
	private String cnpj;

	@Schema(description = "Endereço da empresa.", example = "QE 52 conjunto B casa 88", required = true)
	private String endereco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFanstasia() {
		return nomeFanstasia;
	}

	public void setNomeFanstasia(String nomeFanstasia) {
		this.nomeFanstasia = nomeFanstasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
