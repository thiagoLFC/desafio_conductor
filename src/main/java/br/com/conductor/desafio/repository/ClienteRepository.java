package br.com.conductor.desafio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.conductor.desafio.entidade.Cliente;

/**
 * Interface de acesso aos dados do cliente
 * @author thiago
 *
 */
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	Cliente findByCpf(String cpf);

}
