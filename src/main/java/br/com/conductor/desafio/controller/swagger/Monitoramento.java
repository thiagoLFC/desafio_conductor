package br.com.conductor.desafio.controller.swagger;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class Monitoramento {

	@Schema(description = "Descrição das informações.", example = "Mem: 3024108K used, 9952992K free, 435596K shrd, 92396K buff, 1724124K cached", required = true)
	private List<String>  informacoes = new ArrayList<String>();

	public List<String> getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(List<String> informacoes) {
		this.informacoes = informacoes;
	}

}
