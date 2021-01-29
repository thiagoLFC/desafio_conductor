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

import br.com.conductor.desafio.controller.swagger.IEmpresaController;
import br.com.conductor.desafio.entidade.Empresa;
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
	public ResponseEntity<Object> findAll() {
		try {
			List<Empresa> clientes = empresaService.findAll();
			if(clientes.isEmpty()) {
				return ok(clientes , HttpStatus.NOT_FOUND);
			} else {
				return ok(clientes, HttpStatus.OK);
			}
		} catch (Exception e) {
			return processarErro(e);
		}
	}
	
	/**
	 * Metodo para criar uma empresa
	 * @param empresa Dados da empresa
	 */
	@Override
	@RequestMapping(value = "", method = RequestMethod.POST , consumes = {"application/json"})
	public ResponseEntity<Object> create(@RequestBody Empresa empresa) {
		try {
			empresaService.create(empresa);
			return ok(HttpStatus.CREATED);
		} catch (Exception e) {
			return processarErro(e);
		}
	}
	
	/**
	 * Metodo para consultar por id do cliente
	 * @param id id da empresa
	 * @return Empresa
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> findById(@PathVariable("id") Integer id) {
		try {
			Empresa empresa = empresaService.findById(id);
			if(empresa == null) {
				return ok(empresa, HttpStatus.NOT_FOUND);	
			} else {
				return ok(empresa, HttpStatus.OK);
			}
		} catch (Exception e) {
			return processarErro(e);
		}
	}
	
	/**
	 * Metodo para atualizar uma empresa
	 * @param id id da empresa
	 */
	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT , consumes = {"application/json"})
	public ResponseEntity<Object> update(@PathVariable(value = "id") Integer id, @RequestBody Empresa empresa) {
		try {
			empresaService.update(id, empresa);
			return ok(HttpStatus.OK);
		} catch (Exception e) {
			return processarErro(e);
		}
	}

}
