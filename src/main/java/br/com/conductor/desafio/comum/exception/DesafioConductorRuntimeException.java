package br.com.conductor.desafio.comum.exception;

/**
 * Classe para identificar as execoes runtime da aplicacao
 * @author thiag
 *
 */
public class DesafioConductorRuntimeException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String erro;

	public DesafioConductorRuntimeException(String erro) {
		this.erro = erro;
	}
	
	@Override
	public String getMessage() {
		return erro;
	}


}
