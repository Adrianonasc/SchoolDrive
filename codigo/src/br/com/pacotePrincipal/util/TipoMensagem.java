package br.com.pacotePrincipal.util;

public enum TipoMensagem {

	/**
	 * As descricoes contidas nos enums sao referencias a tags do framework Bootstrap 3.1.1
	 */

	ERRO("danger"), ALERTA("warning"), SUCESSO("success"),VARIAVEL_VIEW("tipo");

	private TipoMensagem(String name) {
		this.valor = name;
	}

	private String valor;

	public String getValor() {
		return this.valor;
	}

}
