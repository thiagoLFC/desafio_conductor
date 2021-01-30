package br.com.conductor.desafio.config.multitenancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.conductor.desafio.comum.Constantes;
import br.com.conductor.desafio.comum.MensagemLoader;
import br.com.conductor.desafio.comum.exception.DesafioConductorRuntimeException;
import br.com.conductor.desafio.enus.Mensagem;

/**
 * Classe para interceptar as a execucao do DS deixando assim dinamico
 * @author thiag
 *
 */
public class ConfigBDRoutingDataSource extends AbstractRoutingDataSource {
	
	@Autowired
	protected MensagemLoader mensagemLoader;
	
    @Override
    protected Object determineCurrentLookupKey() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attr == null) {
        	return Constantes.USUARIO_DEFAULT;
        } else {
        	String usuarioRequest = attr.getRequest().getHeader("user-permission");
        	return getBdByUsuarioRequest(usuarioRequest);
        }
    }

	private String getBdByUsuarioRequest(String usuarioRequest) {
		String bd = null;
		if(Constantes.USUARIO_1.equals(usuarioRequest)) {
			bd = Constantes.BD_CLIENTES_1;
		} else if(Constantes.USUARIO_2.equals(usuarioRequest)) {
			bd = Constantes.BD_CLIENTES_2;
		} else {
			throw new DesafioConductorRuntimeException(mensagemLoader.getMensagem(Mensagem.CLIENTE_SEM_PREMISSAO , usuarioRequest));
		}
		return bd;
	}

}
