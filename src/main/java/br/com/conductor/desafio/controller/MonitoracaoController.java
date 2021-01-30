package br.com.conductor.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.desafio.comum.exception.DesafioConductorNotFoundException;
import br.com.conductor.desafio.controller.swagger.IMonitoracaoController;
import br.com.conductor.desafio.entidade.Monitoracao;
import br.com.conductor.desafio.enus.Mensagem;
import br.com.conductor.desafio.enus.TipoMonitoracao;
import br.com.conductor.desafio.service.MonitoracaoService;

/**
 * Classe para receber as chamadas de monitoramento REST
 * 
 * @author thiago
 *
 */
@RestController
@RequestMapping("/monitoracoes")
public class MonitoracaoController extends DesafioConductorController implements IMonitoracaoController {
	
	@Autowired
	private MonitoracaoService monitoracaoService;
	
	/**
	 * Metodo para consultar as monitoracoes do sistema
	 * @return Lista de monitoracoes
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Monitoracao findAll() {
		Monitoracao monitoracao = monitoracaoService.findAll();
		if(monitoracao.getInformacoes().isEmpty()) {
			throw new DesafioConductorNotFoundException(mensagemLoader.getMensagem(Mensagem.RECURSO_NAO_ENCONTRADO));
		} else {
			return monitoracao;
		}
	}
	
	/**
	 * Metodo para consultar por id a monitoracao
	 * @param id id da monitoracao
	 * @return Monitoracao
	 */
	@Override
	@RequestMapping(value = "/{tipo}", method = RequestMethod.GET)
	public Monitoracao findByTipoMonitoracao(@PathVariable("tipo") TipoMonitoracao tipoMonitoracao) {
		Monitoracao monitoracao = monitoracaoService.findByTipoMonitoracao(tipoMonitoracao);
		if(monitoracao == null) {
			throw new DesafioConductorNotFoundException(mensagemLoader.getMensagem(Mensagem.RECURSO_NAO_ENCONTRADO));
		} else {
			return monitoracao;
		}
	}

}
