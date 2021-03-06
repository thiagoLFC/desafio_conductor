package br.com.conductor.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.desafio.comum.exception.DesafioConductorNotFoundException;
import br.com.conductor.desafio.controller.swagger.IClienteController;
import br.com.conductor.desafio.entidade.Cliente;
import br.com.conductor.desafio.enus.Mensagem;
import br.com.conductor.desafio.service.ClienteService;

/**
 * Classe para receber as chamadas REST de clientes
 * 
 * @author thiago
 *
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController extends DesafioConductorController implements IClienteController {
	
	@Autowired
	private ClienteService clienteService;

	/**
	 * Metodo para consultar todos os clientes
	 * @return Lista de clientes
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Cliente> findAll() {
		List<Cliente> clientes = clienteService.findAll();
		if(clientes.isEmpty()) {
			throw new DesafioConductorNotFoundException(mensagemLoader.getMensagem(Mensagem.RECURSO_NAO_ENCONTRADO));
		} else {
			return clientes;
		}
	}
	
	/**
	 * Metodo para criar um cliente
	 * @param cliente Dados do cliente
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void create(@RequestBody Cliente cliente) {
		clienteService.create(cliente);
	}
	
	/**
	 * Metodo para consultar por id do cliente
	 * @param id id do cliente
	 * @return Cliente
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Cliente findById(@PathVariable("id") Integer id) {
		Cliente cliente = clienteService.findById(id);
		if(cliente == null) {
			throw new DesafioConductorNotFoundException(mensagemLoader.getMensagem(Mensagem.RECURSO_NAO_ENCONTRADO));
		} else {
			return cliente;
		}
	}
	
	/**
	 * Metodo para atualizar um cliente
	 * @param id id do cliente
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable(value = "id") Integer id, @RequestBody Cliente cliente) {
		clienteService.update(id, cliente);
	}
	
	/**
	 * Metodo para remover um cliente
	 * @param id id do cliente
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		clienteService.delete(id);
		ok(HttpStatus.OK);
	}

}
