package br.com.conductor.desafio.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para mapear a monitoracao do sistema
 * @author thiag
 */
public class Monitoracao {
	
	private List<String> informacoes = new ArrayList<String>();

	public Monitoracao(String informacao) {
		this.informacoes.add(informacao);
	}
	
	public Monitoracao() {
	}

	public List<String> getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(List<String> informacoes) {
		this.informacoes = informacoes;
	}

}
