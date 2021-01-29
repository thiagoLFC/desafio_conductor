package br.com.conductor.desafio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.conductor.desafio.comum.exception.DesafioConductorRuntimeException;
import br.com.conductor.desafio.controller.swagger.RetornoInterno;
import br.com.conductor.desafio.controller.swagger.RetornoNegocio;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(DesafioConductorRuntimeException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public RetornoNegocio resourceNotFoundException(DesafioConductorRuntimeException ex, WebRequest request) {
		return new RetornoNegocio(HttpStatus.BAD_REQUEST, ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public RetornoInterno globalExceptionHandler(Exception ex, WebRequest request) {
		if (ex instanceof DesafioConductorRuntimeException) {
			return new RetornoInterno(HttpStatus.BAD_REQUEST, ex.getMessage());
		} else if (ex.getCause() instanceof DesafioConductorRuntimeException) {
			return new RetornoInterno(HttpStatus.BAD_REQUEST, ex.getCause().getMessage());
		} else {
			return new RetornoInterno(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

}
