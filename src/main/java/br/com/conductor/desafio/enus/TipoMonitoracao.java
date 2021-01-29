package br.com.conductor.desafio.enus;

public enum TipoMonitoracao {
	
	MEMORIA,CPU,DISCO;

	public boolean isMemoria() {
		return MEMORIA.equals(this);
	}
	
	public boolean isCpu() {
		return CPU.equals(this);
	}
	
	public boolean isDisco() {
		return DISCO.equals(this);
	}

}
