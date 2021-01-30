package br.com.conductor.desafio.controller.swagger;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public class RetornoNaoEncontrado {

	@Schema(description = "Codigo do erro de retorno.", example = "404", required = true)
	private Integer codigo;
	
	@Schema(description = "Descrição do erro.", example = "Recurso não encontrado.", required = true)
	private String mensagem;

	public RetornoNaoEncontrado(HttpStatus status, String mensagem) {
		this.codigo = status.value();
		this.mensagem = mensagem;
	}
	
	public RetornoNaoEncontrado() {}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
