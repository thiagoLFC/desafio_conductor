package br.com.conductor.desafio.controller.swagger;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;

public class RetornoInterno {

	@Schema(description = "Codigo do erro de retorno.", example = "500", required = true)
	private Integer codigo;
	
	@Schema(description = "Descrição do erro.", example = "Erro interno no servidor.", required = true)
	private String mensagem;

	public RetornoInterno(HttpStatus status, String mensagem) {
		this.codigo = status.value();
		this.mensagem = mensagem;
	}
	
	public RetornoInterno() {}

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
