package br.com.conductor.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.desafio.controller.swagger.IMonitoracaoController;
import br.com.conductor.desafio.entidade.Monitoracao;
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
	public ResponseEntity<Object> findAll() {
		try {
			Monitoracao monitoracoes = monitoracaoService.findAll();
			if(monitoracoes.getInformacoes().isEmpty()) {
				return ok(monitoracoes , HttpStatus.NOT_FOUND);
			} else {
				return ok(monitoracoes, HttpStatus.OK);
			}
		} catch (Exception e) {
			return processarErro(e);
		}
	}
	
	/**
	 * Metodo para consultar por id a monitoracao
	 * @param id id da monitoracao
	 * @return Monitoracao
	 */
	@Override
	@RequestMapping(value = "/{tipo}", method = RequestMethod.GET)
	public ResponseEntity<Object> findByTipoMonitoracao(@PathVariable("tipo") TipoMonitoracao tipoMonitoracao) {
		try {
			Monitoracao monitoracao = monitoracaoService.findByTipoMonitoracao(tipoMonitoracao);
			if(monitoracao == null) {
				return ok(monitoracao, HttpStatus.NOT_FOUND);	
			} else {
				return ok(monitoracao, HttpStatus.OK);
			}
		} catch (Exception e) {
			return processarErro(e);
		}
	}

}
