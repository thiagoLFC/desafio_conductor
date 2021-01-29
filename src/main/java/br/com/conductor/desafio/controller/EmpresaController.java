package br.com.conductor.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conductor.desafio.comum.exception.DesafioConductorRuntimeException;
import br.com.conductor.desafio.controller.swagger.IEmpresaController;
import br.com.conductor.desafio.entidade.Empresa;
import br.com.conductor.desafio.enus.Mensagem;
import br.com.conductor.desafio.service.EmpresaService;

/**
 * Classe para receber as chamadas REST de clientes
 * 
 * @author thiago
 *
 */
@RestController
@RequestMapping("/empresas")
public class EmpresaController extends DesafioConductorController implements IEmpresaController {

	@Autowired
	private EmpresaService empresaService;

	/**
	 * Metodo para consultar todas as empresas
	 * @return Lista de empresas
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Empresa> findAll() {
		List<Empresa> empresas = empresaService.findAll();
		if(empresas.isEmpty()) {
			throw new DesafioConductorRuntimeException(mensagemLoader.getMensagem(Mensagem.RECURSO_NAO_ENCONTRADO));
		} else {
			return empresas;
		}
	}
	
	/**
	 * Metodo para criar uma empresa
	 * @param empresa Dados da empresa
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.POST , consumes = {"application/json"})
	public ResponseEntity<Object> create(@RequestBody Empresa empresa) {
		empresaService.create(empresa);
		return ok(HttpStatus.CREATED);
	}
	
	/**
	 * Metodo para consultar por id do cliente
	 * @param id id da empresa
	 * @return Empresa
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Empresa findById(@PathVariable("id") Integer id) {
		Empresa empresa = empresaService.findById(id);
		if(empresa == null) {
			throw new DesafioConductorRuntimeException(mensagemLoader.getMensagem(Mensagem.RECURSO_NAO_ENCONTRADO));
		} else {
			return empresa;
		}
	}
	
	/**
	 * Metodo para atualizar uma empresa
	 * @param id id da empresa
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT , consumes = {"application/json"})
	public ResponseEntity<Object> update(@PathVariable(value = "id") Integer id, @RequestBody Empresa empresa) {
		empresaService.update(id, empresa);
		return ok(HttpStatus.OK);
	}

}
