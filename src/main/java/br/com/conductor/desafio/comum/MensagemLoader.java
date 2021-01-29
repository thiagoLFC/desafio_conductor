package br.com.conductor.desafio.comum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import br.com.conductor.desafio.enus.Mensagem;

/**
 * Classe para encapsular as mensagens do arquivo .properties do sistema
 * @author thiag
 *
 */
@Component
public class MensagemLoader {

	@Autowired
	private MessageSource messageSource;

	private MessageSourceAccessor accessor;

	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource);
	}

	/**
	 * Metodo para recuperar a mensagem do arquivo
	 * @param mensagem Enum com os dados da mensagem
	 * @param params Parametros da mensagem
	 * @return mensagem formatdaS
	 */
	public String getMensagem(Mensagem mensagem, Object... params) {
		return accessor.getMessage(mensagem.getCodigo(), params);
	}

}
