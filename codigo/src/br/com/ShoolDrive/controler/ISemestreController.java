package br.com.ShoolDrive.controler;

import br.com.ShoolDrive.entidade.Semestre;
import br.com.ShoolDrive.exception.RNException;

/**
 * 
 * 17/12/2014
 * @author Adriano 
 * Classe Responsavel por regras do Semestre
 */
public interface ISemestreController {
	
	public void fecharSemestre(Long id) throws RNException;
	
	
	public <S extends Semestre> S save(S semestre) throws RNException;
	

	public Iterable<Semestre> findAll();
	
	
	public Semestre findOne(Long id);
}
