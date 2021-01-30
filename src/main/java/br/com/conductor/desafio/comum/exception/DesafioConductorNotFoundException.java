package br.com.conductor.desafio.comum.exception;

/**
 * Classe para identificar as execoes runtime da aplicacao
 * @author thiag
 *
 */
public class DesafioConductorNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String erro;

	public DesafioConductorNotFoundException(String erro) {
		this.erro = erro;
	}
	
	@Override
	public String getMessage() {
		return erro;
	}


}