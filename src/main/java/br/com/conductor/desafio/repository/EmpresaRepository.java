package br.com.conductor.desafio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.conductor.desafio.entidade.Empresa;

/**
 * Interface de acesso aos dados da empresa
 * @author thiago
 *
 */
@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Integer> {

	Empresa findByCnpj(String cnpj);

}
