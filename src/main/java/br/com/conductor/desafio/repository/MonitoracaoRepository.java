package br.com.conductor.desafio.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Repository;

import br.com.conductor.desafio.entidade.Monitoracao;
import br.com.conductor.desafio.enus.TipoMonitoracao;

/**
 * Interface de acesso aos dados da monitoracao
 * @author thiago
 *
 */
@Repository
public class MonitoracaoRepository {

	public Monitoracao findAll() {
		return recuperarInformacoesServidor();
	}
	
	public Monitoracao findByTipoMonitoracao(TipoMonitoracao tipoMonitoracao) {
		Monitoracao monitoracao = recuperarInformacoesServidor();
		
		if(tipoMonitoracao.isMemoria()) {
			return new Monitoracao(monitoracao.getInformacoes().get(0));
		} else if (tipoMonitoracao.isCpu()) {
			return new Monitoracao(monitoracao.getInformacoes().get(1));
		} else {
			return new Monitoracao(monitoracao.getInformacoes().get(2));
		}
	}
	
	/**
	 * Metodo para recuperar a saude do container
	 * @return Monitoracao
	 */
	private Monitoracao recuperarInformacoesServidor() {
		Monitoracao monitoracao = new Monitoracao();

		ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("top", "-bn1");
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String linha;
            while ((linha = reader.readLine()) != null) {
            	monitoracao.getInformacoes().add(linha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monitoracao;
	}
	
}
