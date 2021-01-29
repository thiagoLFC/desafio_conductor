package br.com.conductor.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.conductor.desafio.comum.MensagemLoader;

/**
 * Classe para tratar as informacoes comuns das controllers
 * @author thiag
 *
 */
public abstract class DesafioConductorController {
	
	@Autowired
	protected MensagemLoader mensagemLoader;
	
	protected ResponseEntity<Object> ok(Object body , HttpStatus status) {
		return new ResponseEntity<Object>(body , status);
	}
	
	protected ResponseEntity<Object> ok(HttpStatus status) {
		return new ResponseEntity<Object>(null , status);
	}
	
}
