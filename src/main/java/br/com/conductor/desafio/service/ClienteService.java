package br.com.conductor.desafio.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conductor.desafio.comum.StringUtil;
import br.com.conductor.desafio.comum.exception.DesafioConductorRuntimeException;
import br.com.conductor.desafio.entidade.Cliente;
import br.com.conductor.desafio.enus.Mensagem;
import br.com.conductor.desafio.repository.ClienteRepository;

/**
 * Classe para implementar os servicos de cliente
 * @author thiag
 *
 */
@Service
public class ClienteService extends DesafioConductorService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	/**
	 * Metodo para consultar todos os clientes
	 * @return Lista de clientes
	 */
	@Transactional(value = TxType.NOT_SUPPORTED)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	/**
	 * Metodo para criar um cliente
	 * @param cliente Dados do cliente
	 */
	@Transactional(value = TxType.REQUIRED)
	public void create(Cliente cliente) {
		isClienteValido(cliente);
		
		clienteRepository.save(cliente);
	}
	
	/**
	 * Consultar um cliente pelo id
	 * @param id Id do cliente
	 * @return Cliente
	 */
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Cliente findById(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	/**
	 * Metodo para atualizar um cliente
	 * @param id id do cliente
	 * @param cliente Dados do cliente
	 */
	@Transactional(value = TxType.REQUIRED)
	public void update(Integer id, Cliente cliente) {
		Cliente clienteBd = hasClienteById(id);
		atualizarDadosClienteBd(clienteBd , cliente);
		clienteRepository.save(clienteBd);
	}

	/**
	 * Metodo para verificar os atributos que serao atualizados na entidade
	 * @param clienteBd Dados do cliente do banco
	 * @param cliente Cliente para ser atualizado
	 */
	private void atualizarDadosClienteBd(Cliente clienteBd, Cliente cliente) {
		clienteBd.setCpf(cliente.getCpf() == null ? clienteBd.getCpf() : cliente.getCpf());
		clienteBd.setNome(cliente.getNome() == null ? clienteBd.getNome() : cliente.getNome());
		if(cliente.getEmail() != null) {
			isEmailValido(cliente.getEmail());
			clienteBd.setEmail(cliente.getEmail() == null ? clienteBd.getEmail() : cliente.getEmail());
		}
	}

	/**
	 * Metodo para verificar se o cliente existe
	 * @param id Id do cliente
	 * @return Dados do cliente
	 */
	private Cliente hasClienteById(Integer id) {
		Cliente clienteBD = findById(id);
		if(clienteBD == null) {
			throw new DesafioConductorRuntimeException(mensagemLoader.getMensagem(Mensagem.CLIENTE_INEXISTENTE, id));
		}
		return clienteBD;
	}

	/**
	 * Metodo para realizar as validacaoes para gravar o cliente
	 * @param cliente Dados do cliente
	 */
	private void isClienteValido(Cliente cliente) {
		hasCpf(cliente.getCpf());
		isEmailValido(cliente.getEmail());
	}

	/**
	 * Metodo para verificar se o email e valido ou nao
	 * @param email Dados do email
	 */
	private void isEmailValido(String email) {
		if(!StringUtil.isEmailValido(email)) {
			throw new DesafioConductorRuntimeException(mensagemLoader.getMensagem(Mensagem.EMAIL_INVALIDO, email));
		}
	}

	/**
	 * Metodo para verificar se o CPF ja existe
	 * @param cpf Dados do CPF
	 */
	private void hasCpf(String cpf) {
		Cliente clienteCpf = clienteRepository.findByCpf(cpf);
		if(clienteCpf != null) {
			throw new DesafioConductorRuntimeException(mensagemLoader.getMensagem(Mensagem.CLIENTE_JA_CADASTRADO, cpf));
		}
	}

}
