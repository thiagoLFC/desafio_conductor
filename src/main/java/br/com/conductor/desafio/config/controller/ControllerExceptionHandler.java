package br.com.conductor.desafio.config.controller;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.conductor.desafio.comum.exception.DesafioConductorNotFoundException;
import br.com.conductor.desafio.comum.exception.DesafioConductorRuntimeException;
import br.com.conductor.desafio.controller.swagger.RetornoInterno;
import br.com.conductor.desafio.controller.swagger.RetornoNegocio;

/**
 * Classe para tratar as execoes do sistema e retornar para API Rest os codigos e mensagens
 * @author thiag
 *
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(DesafioConductorNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public RetornoNegocio resourceNotFoundException(DesafioConductorNotFoundException ex, WebRequest request) {
		return new RetornoNegocio(HttpStatus.NOT_FOUND, ex.getMessage());
	}
	
	@ExceptionHandler(DesafioConductorRuntimeException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public RetornoNegocio resourceBadRequestException(DesafioConductorRuntimeException ex, WebRequest request) {
		return new RetornoNegocio(HttpStatus.BAD_REQUEST, ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public RetornoInterno globalExceptionHandler(Exception ex, WebRequest request) {
		return new RetornoInterno(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}
	
	@ExceptionHandler(CannotCreateTransactionException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public RetornoNegocio resourceBadRequestCreateTransacionException(CannotCreateTransactionException ex, WebRequest request) {
		return new RetornoNegocio(HttpStatus.BAD_REQUEST, ex.getCause().getMessage());
	}

}
