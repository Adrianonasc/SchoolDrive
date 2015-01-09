package br.com.ShoolDrive.util;

/**
 * 08/01/2015
 * @author Adriano <p>
 * ENUM Responsavel por POR CLASSIFICAR SE O ENVIO DE TRABALHO ESTA DISCPONIVEL
 */
public enum Status {

	Aberto(true), Fechado(false);

	private Boolean status;

	private Status(boolean status) {}

	public Boolean getStatus() {
		return this.status;
	}
}
