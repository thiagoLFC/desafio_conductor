package br.com.conductor.desafio.controller.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

public class Cliente {

	@Schema(description = "Identificador do cliente.", example = "1", required = true)
	private Integer id;

	@Schema(description = "Nome do cliente.", example = "Nome do Cliente", required = true)
	private String nome;

	@Schema(description = "CPF do cliente.", example = "12345678963", required = true)
	private String cpf;

	@Schema(description = "E-mail do cliente.", example = "123@123.com", required = true)
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
