package br.com.conductor.desafio.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conductor.desafio.comum.exception.DesafioConductorRuntimeException;
import br.com.conductor.desafio.entidade.Empresa;
import br.com.conductor.desafio.enus.Mensagem;
import br.com.conductor.desafio.repository.EmpresaRepository;

/**
 * Classe para implementar os servicos da empresa
 * @author thiag
 *
 */
@Service
public class EmpresaService extends DesafioConductorService {
	
	@Autowired
	private EmpresaRepository empresaRepository;

	/**
	 * Metodo para consultar todas as empresas
	 * @return Lista de clientes
	 */
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Empresa> findAll() {
		return (List<Empresa>) empresaRepository.findAll();
	}

	/**
	 * Consultar uma empresa pelo id
	 * @param id Id da empresa
	 * @return Empresa
	 */
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Empresa findById(Integer id) {
		return empresaRepository.findById(id).orElse(null);
	}

	/**
	 * Metodo para criar uma empresa
	 * @param empresa Dados da empresa
	 */
	@Transactional(value = TxType.REQUIRED)
	public void create(Empresa empresa) {
		isEmpresaValida(empresa);
		
		empresaRepository.save(empresa);
	}

	/**
	 * Metodo para verificar se a empresa e valida ou nao
	 * @param empresa Dados da emprsa
	 */
	private void isEmpresaValida(Empresa empresa) {
		hasCnpj(empresa.getCnpj());
	}

	/**
	 * Metodo para verificar se o CNPJ ja existe
	 * @param cnpj Dados do CNPJ
	 */
	private void hasCnpj(String cnpj) {
		Empresa empresa = empresaRepository.findByCnpj(cnpj);
		if(empresa != null) {
			throw new DesafioConductorRuntimeException(mensagemLoader.getMensagem(Mensagem.EMPRESA_JA_CADASTRADA, cnpj));
		}
	}

	/**
	 * Metodo para atualizar uma empresa
	 * @param id id da empresa
	 * @param empresa Dados da empresa
	 */
	@Transactional(value = TxType.REQUIRED)
	public void update(Integer id, Empresa empresa) {
		Empresa empresaBd = hasEmpresaById(id);
		atualizarDadosEmpresaBd(empresaBd , empresa);
		empresaRepository.save(empresaBd);
	}

	/**
	 * Metodo para verificar os atributos que serao atualizados na entidade
	 * @param clienteBd Dados do cliente do banco
	 * @param cliente Cliente para ser atualizado
	 */
	private void atualizarDadosEmpresaBd(Empresa empresaBd, Empresa empresa) {
		empresaBd.setEndereco(empresa.getEndereco() == null ? empresaBd.getEndereco(): empresa.getEndereco());
		empresaBd.setCnpj(empresa.getCnpj() == null ? empresaBd.getCnpj(): empresa.getCnpj());
		empresaBd.setNomeFanstasia(empresa.getNomeFanstasia() == null ? empresaBd.getNomeFanstasia() : empresa.getNomeFanstasia());
	}

	/**
	 * Metodo para verificar se a empresa existe
	 * @param id Id da empresa
	 * @return Dados da empresa
	 */
	private Empresa hasEmpresaById(Integer id) {
		Empresa empresaBD = findById(id);
		if(empresaBD == null) {
			throw new DesafioConductorRuntimeException(mensagemLoader.getMensagem(Mensagem.EMPRESA_INEXISTENTE, id));
		}
		return empresaBD;
	}

}
