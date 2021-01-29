package br.com.conductor.desafio.controller.swagger;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public class RetornoNegocio {

	@Schema(description = "Codigo do erro de retorno.", example = "400", required = true)
	private Integer codigo;
	
	@Schema(description = "Descrição do erro.", example = "Informação já cadastrada", required = true)
	private String mensagem;

	public RetornoNegocio(HttpStatus status, String mensagem) {
		this.codigo = status.value();
		this.mensagem = mensagem;
	}
	
	public RetornoNegocio() {}

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
