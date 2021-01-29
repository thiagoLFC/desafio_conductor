package br.com.conductor.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.conductor.desafio.comum.MensagemLoader;

/**
 * Classe comum a todos os servicos
 * @author thiag
 *
 */
public abstract class DesafioConductorService {
	
	@Autowired
	protected MensagemLoader mensagemLoader;

}
