package br.com.conductor.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.conductor.desafio.comum.MensagemLoader;
import br.com.conductor.desafio.comum.exception.DesafioConductorRuntimeException;
import br.com.conductor.desafio.controller.swagger.RetornoNegocio;

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
	
	protected ResponseEntity<Object> processarErro(Exception e) {
		if(e instanceof DesafioConductorRuntimeException) {
			return new ResponseEntity<Object>(new RetornoNegocio(HttpStatus.BAD_REQUEST , e.getMessage()) , HttpStatus.BAD_REQUEST);
		} else if(e.getCause() instanceof DesafioConductorRuntimeException) {
			return new ResponseEntity<Object>(new RetornoNegocio(HttpStatus.BAD_REQUEST , e.getCause().getMessage()) , HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Object>(new RetornoNegocio(HttpStatus.INTERNAL_SERVER_ERROR , e.getCause().getMessage()) , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
