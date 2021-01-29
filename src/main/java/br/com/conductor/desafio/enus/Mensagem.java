package br.com.conductor.desafio.enus;

/**
 * Enum para mapear as mensagens do sistema
 * @author thiag
 *
 */
public enum Mensagem {

	CLIENTE_JA_CADASTRADO("cliente_ja_cadastrado"),
	CLIENTE_INEXISTENTE("cliente_inexistente"),
	
	EMPRESA_JA_CADASTRADA("empresa_ja_cadastrada"),
	EMPRESA_INEXISTENTE("empresa_inexistente"),
	
	EMAIL_INVALIDO("email_invalido"),
	OPERACAO_REALIZADA_SUCESSSO("operacao_realizada_sucessso"), 
	
	RECURSO_NAO_ENCONTRADO("recurso_nao_encontrado"),
	CLIENTE_SEM_PREMISSAO("cliente_sem_premissao"),
	;
	
	private String codigo;
	
	private Mensagem(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCodigo() {
		return codigo;
	}
}