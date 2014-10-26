package br.com.pacotePrincipal.exception;

/**
 * Classe Reponsavel Pelo por lançar a exeção sobre a regra de Negocio
 * @author Adriano
 *
 */
public class RNException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensagem;
	
	public RNException(String mensagens) {
		this.mensagem = mensagens;
	}
	public String getMensagem() {
		return mensagem;
	}
}
