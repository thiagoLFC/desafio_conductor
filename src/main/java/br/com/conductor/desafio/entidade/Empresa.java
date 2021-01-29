package br.com.conductor.desafio.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe para mapear o cliente
 * @author thiag
 */
@Entity
@Table(name = "EMPRESA")
public class Empresa extends DesafioConductorEntidade {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOME_FANSTASIA", nullable = false)
	private String nomeFanstasia;

	@Column(name = "CNPJ", nullable = false)
	private String cnpj;

	@Column(name = "ENDERECO", nullable = false)
	private String endereco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFanstasia() {
		return nomeFanstasia;
	}

	public void setNomeFanstasia(String nomeFanstasia) {
		this.nomeFanstasia = nomeFanstasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
