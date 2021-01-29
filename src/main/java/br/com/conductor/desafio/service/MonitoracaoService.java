package br.com.conductor.desafio.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conductor.desafio.entidade.Monitoracao;
import br.com.conductor.desafio.enus.TipoMonitoracao;
import br.com.conductor.desafio.repository.MonitoracaoRepository;

/**
 * Classe para implementar os servicos de monitoracao
 * @author thiag
 *
 */
@Service
public class MonitoracaoService {
	
	@Autowired
	private MonitoracaoRepository monitoracaoRepository;

	/**
	 * Metodo para consultar as monitoracoes do docker
	 * @return Lista de Monitoracao
	 */
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Monitoracao findAll() {
		return monitoracaoRepository.findAll();
	}
	
	/**
	 * @return Lista de Monitoracao
	 */
	/**
	 * Metodo para consultar um tipo de monitoracao do docker
	 * @param tipoMonitoracao TipoMonitoracao
	 * @return Monitoracao
	 */
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Monitoracao findByTipoMonitoracao(TipoMonitoracao tipoMonitoracao) {
		return monitoracaoRepository.findByTipoMonitoracao(tipoMonitoracao);
	}

}
